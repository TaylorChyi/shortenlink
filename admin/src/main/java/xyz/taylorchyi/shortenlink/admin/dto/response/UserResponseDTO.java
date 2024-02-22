package xyz.taylorchyi.shortenlink.admin.dto.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import xyz.taylorchyi.shortenlink.admin.common.serialize.PhoneDesensitizedSerializer;

@Data
public class UserResponseDTO {
    private long id;
    private String username;
    private String realName;

    @JsonSerialize(using = PhoneDesensitizedSerializer.class)
    private String phone;
    private String email;
}
