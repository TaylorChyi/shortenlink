package xyz.taylorchyi.shortenlink.admin.dao.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.taylorchyi.shortenlink.admin.common.database.BaseDO;

@Data
@TableName("shorten_link_group")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupDO extends BaseDO {

  private long id;
  private String groupId;
  private String name;
  private String createUsername;
  private Integer sortOrder;
}
