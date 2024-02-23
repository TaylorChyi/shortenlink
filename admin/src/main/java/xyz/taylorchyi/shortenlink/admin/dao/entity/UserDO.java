package xyz.taylorchyi.shortenlink.admin.dao.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import xyz.taylorchyi.shortenlink.admin.common.database.BaseDO;

@Data
@TableName("user")
public class UserDO extends BaseDO {

  private long id;
  private String username;
  private String password;
  private String realName;
  private String phone;
  private String email;
}
