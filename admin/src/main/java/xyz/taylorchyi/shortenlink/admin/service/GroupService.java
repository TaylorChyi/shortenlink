package xyz.taylorchyi.shortenlink.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import xyz.taylorchyi.shortenlink.admin.dao.entity.GroupDO;

public interface GroupService  extends IService<GroupDO> {
    void saveGroup(String groupName);
}
