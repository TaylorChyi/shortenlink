package xyz.taylorchyi.shortenlink.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import xyz.taylorchyi.shortenlink.admin.dao.entity.GroupDO;
import xyz.taylorchyi.shortenlink.admin.dto.response.shortenlink.ShortenLinkGroupResponseDTO;

import java.util.List;

public interface GroupService  extends IService<GroupDO> {
    void saveGroup(String groupName);

    List<ShortenLinkGroupResponseDTO> listGroup();
}
