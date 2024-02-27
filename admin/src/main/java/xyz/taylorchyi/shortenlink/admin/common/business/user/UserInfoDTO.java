package xyz.taylorchyi.shortenlink.admin.common.business.user;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInfoDTO {

    @JSONField(name = "id")
    private String userId;
    private String username;
    private String realName;
}
