package xyz.taylorchyi.shortenlink.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import xyz.taylorchyi.shortenlink.admin.dao.entity.UserDO;
import xyz.taylorchyi.shortenlink.admin.dto.request.user.UserLoginRequestDTO;
import xyz.taylorchyi.shortenlink.admin.dto.request.user.UserRegisterRequestDTO;
import xyz.taylorchyi.shortenlink.admin.dto.request.user.UserUpdateRequestDTO;
import xyz.taylorchyi.shortenlink.admin.dto.response.user.UserLoginResponseDTO;
import xyz.taylorchyi.shortenlink.admin.dto.response.user.UserResponseDTO;

public interface UserService extends IService<UserDO> {
    UserResponseDTO getUserByUsername(String username);

    Boolean doesUsernameExist(String username);

    void register(UserRegisterRequestDTO userRegisterRequestDTO);

    void update(UserUpdateRequestDTO userUpdateRequestDTO);

    UserLoginResponseDTO login(UserLoginRequestDTO userLoginRequestDTO);

    Boolean doesUserLogin(String userLoginToken);

    Boolean logout(String userLoginToken);
}
