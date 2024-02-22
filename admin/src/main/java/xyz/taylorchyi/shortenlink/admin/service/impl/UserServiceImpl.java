package xyz.taylorchyi.shortenlink.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RBloomFilter;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import xyz.taylorchyi.shortenlink.admin.common.convention.exception.ClientException;
import xyz.taylorchyi.shortenlink.admin.common.enums.errorcode.ClientErrorCode;
import xyz.taylorchyi.shortenlink.admin.dao.entity.UserDO;
import xyz.taylorchyi.shortenlink.admin.dao.mapper.UserMapper;
import xyz.taylorchyi.shortenlink.admin.dto.request.UserRegisterRequestDTO;
import xyz.taylorchyi.shortenlink.admin.dto.response.UserResponseDTO;
import xyz.taylorchyi.shortenlink.admin.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {

    private final RBloomFilter<String> userRegisterCachePenetrationBloomFilter;

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
        int inserted = baseMapper.insert(BeanUtil.toBean(userRegisterRequestDTO, UserDO.class));
        if (inserted < 1) {
            throw new ClientException(ClientErrorCode.REGISTRATION_ERROR);
        }
        userRegisterCachePenetrationBloomFilter.add(userRegisterRequestDTO.getUsername());
    }
}
