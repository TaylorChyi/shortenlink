package xyz.taylorchyi.shortenlink.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import groovy.util.logging.Slf4j;
import org.springframework.stereotype.Service;
import xyz.taylorchyi.shortenlink.admin.dao.entity.GroupDO;
import xyz.taylorchyi.shortenlink.admin.dao.mapper.GroupMapper;
import xyz.taylorchyi.shortenlink.admin.dto.response.shortenlink.ShortenLinkGroupResponseDTO;
import xyz.taylorchyi.shortenlink.admin.service.GroupService;
import xyz.taylorchyi.shortenlink.admin.util.RandomGenerator;

import java.util.List;

@Slf4j
@Service
public class GroupServiceImpl extends ServiceImpl<GroupMapper, GroupDO> implements GroupService {

    @Override
    public void saveGroup(String groupName) {
        String groupId;
         do {
            groupId = RandomGenerator.generateSixRandomCharString();
        } while(isGroupIdRepeated(groupId));
        GroupDO groupDO = GroupDO.builder()
                .groupId(groupId)
                .name(groupName)
                .sortOrder(0)
                .build();
        baseMapper.insert(groupDO);
    }

    @Override
    public List<ShortenLinkGroupResponseDTO> listGroup() {
        LambdaQueryWrapper<GroupDO> queryWrapper = Wrappers.lambdaQuery(GroupDO.class)
                                                    .eq(GroupDO::getDeleteFlag, 0)
                                                    // TODO set search condition : create username;
                                                    .orderByDesc(GroupDO::getSortOrder, GroupDO::getUpdateTime);

        List<GroupDO> groupDOList = baseMapper.selectList(queryWrapper);
        return BeanUtil.copyToList(groupDOList, ShortenLinkGroupResponseDTO.class);
    }

    private boolean isGroupIdRepeated (String groupId) {
        LambdaQueryWrapper<GroupDO> queryWrapper = Wrappers.lambdaQuery(GroupDO.class)
                .eq(GroupDO::getGroupId, groupId)
                // TODO set create username;
                .eq(GroupDO::getCreateUsername, null);

        GroupDO hasGroupFlag = baseMapper.selectOne(queryWrapper);

        return hasGroupFlag != null;
    }
}
