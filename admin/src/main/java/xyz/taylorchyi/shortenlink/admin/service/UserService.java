package xyz.taylorchyi.shortenlink.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import xyz.taylorchyi.shortenlink.admin.dao.entity.UserDO;
import xyz.taylorchyi.shortenlink.admin.dto.request.UserLoginRequestDTO;
import xyz.taylorchyi.shortenlink.admin.dto.request.UserRegisterRequestDTO;
import xyz.taylorchyi.shortenlink.admin.dto.request.UserUpdateRequestDTO;
import xyz.taylorchyi.shortenlink.admin.dto.response.UserLoginResponseDTO;
import xyz.taylorchyi.shortenlink.admin.dto.response.UserResponseDTO;

public interface UserService extends IService<UserDO> {
    UserResponseDTO getUserByUsername(String username);

    Boolean doesUsernameExist(String username);

    void register(UserRegisterRequestDTO userRegisterRequestDTO);

    void update(UserUpdateRequestDTO userUpdateRequestDTO);

    UserLoginResponseDTO login(UserLoginRequestDTO userLoginRequestDTO);

    Boolean doesUserLogin(String userLoginToken);

    Boolean logout(String userLoginToken);
}
