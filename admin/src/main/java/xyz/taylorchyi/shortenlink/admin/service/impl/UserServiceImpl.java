package xyz.taylorchyi.shortenlink.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.UUID;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import xyz.taylorchyi.shortenlink.admin.common.constant.RedisCacheConstant;
import xyz.taylorchyi.shortenlink.admin.common.convention.exception.ClientException;
import xyz.taylorchyi.shortenlink.admin.common.enums.errorcode.ClientErrorCode;
import xyz.taylorchyi.shortenlink.admin.dao.entity.UserDO;
import xyz.taylorchyi.shortenlink.admin.dao.mapper.UserMapper;
import xyz.taylorchyi.shortenlink.admin.dto.request.UserLoginRequestDTO;
import xyz.taylorchyi.shortenlink.admin.dto.request.UserRegisterRequestDTO;
import xyz.taylorchyi.shortenlink.admin.dto.request.UserUpdateRequestDTO;
import xyz.taylorchyi.shortenlink.admin.dto.response.UserLoginResponseDTO;
import xyz.taylorchyi.shortenlink.admin.dto.response.UserResponseDTO;
import xyz.taylorchyi.shortenlink.admin.service.UserService;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {

    private final RBloomFilter<String> userRegisterCachePenetrationBloomFilter;
    private final RedissonClient redissonClient;
    private final StringRedisTemplate hasUserLoggedInRedisTemplate;
    private final StringRedisTemplate loggedInuUserInfoRedisTemplate;

    @Override
    public UserResponseDTO getUserByUsername(String username) {
        LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery(UserDO.class)
                .eq(UserDO::getUsername, username);

        UserDO userDO = baseMapper.selectOne(queryWrapper);
        if (userDO == null) {
            throw new ClientException(ClientErrorCode.USER_DOES_NOT_EXIST);
        }
        UserResponseDTO result = new UserResponseDTO();
        BeanUtils.copyProperties(userDO, result);
        return result;
    }

    @Override
    public Boolean doesUsernameExist(String username) {
        return userRegisterCachePenetrationBloomFilter.contains(username);
    }

    @Override
    public void register(UserRegisterRequestDTO userRegisterRequestDTO) {
        if (doesUsernameExist(userRegisterRequestDTO.getUsername())) {
            throw new ClientException(ClientErrorCode.USERNAME_ALREADY_EXISTS);
        }
        RLock rLock = redissonClient.getLock(RedisCacheConstant.LOCK_USER_REGISTER_KEY + userRegisterRequestDTO.getUsername());
        try {
            if ( rLock.tryLock() ) {
                int inserted = baseMapper.insert(BeanUtil.toBean(userRegisterRequestDTO, UserDO.class));
                if (inserted < 1) {
                    throw new ClientException(ClientErrorCode.REGISTRATION_ERROR);
                }
                userRegisterCachePenetrationBloomFilter.add(userRegisterRequestDTO.getUsername());
            }
            // safety net strategy
            else {
                throw new ClientException(ClientErrorCode.USERNAME_ALREADY_EXISTS);
            }
        }
        finally {
            rLock.unlock();
        }
    }

    @Override
    public void update(UserUpdateRequestDTO userUpdateRequestDTO) {
        // TODO verify the current user is the login user;
        LambdaQueryWrapper<UserDO> updateWrapper = Wrappers.lambdaQuery(UserDO.class).eq(UserDO::getUsername, userUpdateRequestDTO.getUsername());
        baseMapper.update(BeanUtil.toBean(userUpdateRequestDTO, UserDO.class), updateWrapper);
    }

    @Override
    public UserLoginResponseDTO login(UserLoginRequestDTO userLoginRequestDTO) {
        LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery(UserDO.class)
                .eq(UserDO::getUsername, userLoginRequestDTO.getUsername())
                .eq(UserDO::getPassword, userLoginRequestDTO.getPassword())
                .eq(UserDO::getDeleteFlag, 0);
        UserDO userDO = baseMapper.selectOne(queryWrapper);
        if (userDO == null) {
            throw new ClientException(ClientErrorCode.USER_DOES_NOT_EXIST);
        }

        Boolean hasUserLoggedIn = hasUserLoggedInRedisTemplate.hasKey(userLoginRequestDTO.getUsername());
        if (hasUserLoggedIn != null && hasUserLoggedIn) {
            throw new ClientException(ClientErrorCode.USER_HAS_ALREADY_LOGGED_IN);
        }
        else {
            String uuid = UUID.randomUUID().toString();
            hasUserLoggedInRedisTemplate.opsForValue().set(userLoginRequestDTO.getUsername(), uuid, 30L, TimeUnit.MINUTES);
            loggedInuUserInfoRedisTemplate.opsForValue().set(uuid, JSON.toJSONString(userDO), 30L, TimeUnit.MINUTES);
            return new UserLoginResponseDTO(uuid);
        }
    }

    @Override
    public Boolean doesUserLogin(String userLoginToken) {
        return loggedInuUserInfoRedisTemplate.hasKey(userLoginToken);
    }
}
