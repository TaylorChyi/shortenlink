package xyz.taylorchyi.shortenlink.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import xyz.taylorchyi.shortenlink.admin.common.convention.exception.ClientException;
import xyz.taylorchyi.shortenlink.admin.common.convention.result.Result;
import xyz.taylorchyi.shortenlink.admin.common.convention.result.Results;
import xyz.taylorchyi.shortenlink.admin.common.enums.errorcode.ClientErrorCode;
import xyz.taylorchyi.shortenlink.admin.dto.response.UserResponseDTO;
import xyz.taylorchyi.shortenlink.admin.service.UserService;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/api/shortenlink/v1/user/{username}")
    public Result<UserResponseDTO> getUserByUsername(@PathVariable("username") String username) {
        UserResponseDTO result = userService.getUserByUsername(username);
        if (result == null) {
            throw new ClientException(ClientErrorCode.USER_DOES_NOT_EXIST);
        }
        else {
            return Results.success(result);
        }
    }
}