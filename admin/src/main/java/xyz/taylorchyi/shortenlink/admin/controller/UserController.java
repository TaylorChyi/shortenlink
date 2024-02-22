package xyz.taylorchyi.shortenlink.admin.controller;

import cn.hutool.core.bean.BeanUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import xyz.taylorchyi.shortenlink.admin.common.convention.result.Result;
import xyz.taylorchyi.shortenlink.admin.common.convention.result.Results;
import xyz.taylorchyi.shortenlink.admin.dto.response.UserResponseDTO;
import xyz.taylorchyi.shortenlink.admin.dto.response.UserWithSensitiveResponseDTO;
import xyz.taylorchyi.shortenlink.admin.service.UserService;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/api/shortenlink/v1/user/{username}")
    public Result<UserResponseDTO> getUserByUsername(@PathVariable("username") String username) {
        UserResponseDTO result = userService.getUserByUsername(username);
        return Results.success(result);
    }

    @GetMapping("/api/shortenlink/v1/user/sensitive/{username}")
    public Result<UserWithSensitiveResponseDTO> getUserByUsernameWithSensitive(@PathVariable("username") String username) {
        UserResponseDTO result = userService.getUserByUsername(username);
        return Results.success(BeanUtil.toBean(result, UserWithSensitiveResponseDTO.class));
    }
}
