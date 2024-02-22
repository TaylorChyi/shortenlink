package xyz.taylorchyi.shortenlink.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import xyz.taylorchyi.shortenlink.admin.dao.entity.UserDO;
import xyz.taylorchyi.shortenlink.admin.dao.mapper.UserMapper;
import xyz.taylorchyi.shortenlink.admin.dto.response.UserResponseDTO;
import xyz.taylorchyi.shortenlink.admin.service.UserService;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {
    @Override
    public UserResponseDTO getUserByUsername(String username) {
        LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery(UserDO.class)
                .eq(UserDO::getUsername, username);

        UserDO userDO = baseMapper.selectOne(queryWrapper);
        if (userDO == null) {
            return null;
        }
        UserResponseDTO result = new UserResponseDTO();
        BeanUtils.copyProperties(userDO, result);
        return result;
    }
}
