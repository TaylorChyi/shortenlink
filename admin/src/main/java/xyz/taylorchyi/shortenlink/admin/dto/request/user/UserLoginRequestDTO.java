package xyz.taylorchyi.shortenlink.admin.dto.request.user;

import lombok.Data;

@Data
public class UserLoginRequestDTO {
    private String username;
    private String password;
}
