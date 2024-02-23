package xyz.taylorchyi.shortenlink.admin.dto.request;

import lombok.Data;

@Data
public class UserLoginRequestDTO {
    private String username;
    private String password;
}
