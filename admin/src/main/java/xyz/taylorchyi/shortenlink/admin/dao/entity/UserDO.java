package xyz.taylorchyi.shortenlink.admin.dao.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("user")
public class UserDO {

  private long id;
  private String username;
  private String password;
  private String realName;
  private String phone;
  private String email;
  private Date deleteTime;
  private Date createTime;
  private Date updateTime;
  private long deleteFlag;
}
