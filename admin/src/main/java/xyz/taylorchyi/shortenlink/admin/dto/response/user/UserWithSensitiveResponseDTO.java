package xyz.taylorchyi.shortenlink.admin.dto.response.user;

import lombok.Data;

@Data
public class UserWithSensitiveResponseDTO {
    private long id;
    private String username;
    private String realName;
    private String phone;
    private String email;
}
