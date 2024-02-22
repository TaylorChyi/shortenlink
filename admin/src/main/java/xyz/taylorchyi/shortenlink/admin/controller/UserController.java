package xyz.taylorchyi.shortenlink.admin.controller;

import cn.hutool.core.bean.BeanUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import xyz.taylorchyi.shortenlink.admin.common.convention.result.Result;
import xyz.taylorchyi.shortenlink.admin.common.convention.result.Results;
import xyz.taylorchyi.shortenlink.admin.dto.request.UserRegisterRequestDTO;
import xyz.taylorchyi.shortenlink.admin.dto.request.UserUpdateRequestDTO;
import xyz.taylorchyi.shortenlink.admin.dto.response.UserResponseDTO;
import xyz.taylorchyi.shortenlink.admin.dto.response.UserWithSensitiveResponseDTO;
import xyz.taylorchyi.shortenlink.admin.service.UserService;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/api/shorten-link/v1/user/{username}")
    public Result<UserResponseDTO> getUserByUsername(@PathVariable("username") String username) {
        UserResponseDTO result = userService.getUserByUsername(username);
        return Results.success(result);
    }

    @GetMapping("/api/shorten-link/v1/user/sensitive/{username}")
    public Result<UserWithSensitiveResponseDTO> getUserByUsernameWithSensitive(@PathVariable("username") String username) {
        UserResponseDTO result = userService.getUserByUsername(username);
        return Results.success(BeanUtil.toBean(result, UserWithSensitiveResponseDTO.class));
    }

    @GetMapping("/api/shorten-link/v1/user/does-username-exist/{username}")
    public Result<Boolean> doesUsernameExist(@PathVariable("username") String username) {
        Boolean result = userService.doesUsernameExist(username);
        return Results.success(result);
    }

    @PostMapping("/api/shorten-link/v1/user")
    public Result<Void> register(@RequestBody UserRegisterRequestDTO userRegisterRequestDTO) {
        userService.register(userRegisterRequestDTO);
        return Results.success();
    }

    @PutMapping("/api/shorten-link/v1/user")
    public Result<Void> update(@RequestBody UserUpdateRequestDTO userUpdateRequestDTO) {
        userService.update(userUpdateRequestDTO);
        return Results.success();
    }
}
