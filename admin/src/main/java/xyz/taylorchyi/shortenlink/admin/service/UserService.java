package xyz.taylorchyi.shortenlink.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import xyz.taylorchyi.shortenlink.admin.dao.entity.UserDO;
import xyz.taylorchyi.shortenlink.admin.dto.response.UserResponseDTO;

public interface UserService extends IService<UserDO> {
    UserResponseDTO getUserByUsername(String username);

    Boolean doesUsernameExist(String username);
}
