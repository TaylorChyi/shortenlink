package xyz.taylorchyi.shortenlink.admin.dao.entity;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
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

  @TableField(fill = FieldFill.INSERT)
  private Date createTime;
  @TableField(fill = FieldFill.INSERT)
  private Date updateTime;
  @TableField(fill = FieldFill.INSERT)
  private long deleteFlag;
}
