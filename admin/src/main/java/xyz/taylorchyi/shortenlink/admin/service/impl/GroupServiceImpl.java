package xyz.taylorchyi.shortenlink.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import groovy.util.logging.Slf4j;
import org.springframework.stereotype.Service;
import xyz.taylorchyi.shortenlink.admin.common.business.user.UserContext;
import xyz.taylorchyi.shortenlink.admin.dao.entity.GroupDO;
import xyz.taylorchyi.shortenlink.admin.dao.mapper.GroupMapper;
import xyz.taylorchyi.shortenlink.admin.dto.request.shortenlink.ShortenLinkGroupSortRequestDTO;
import xyz.taylorchyi.shortenlink.admin.dto.request.shortenlink.ShortenLinkGroupUpdateRequestDTO;
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
                .createUsername(UserContext.getUsername())
                .name(groupName)
                .sortOrder(0)
                .build();
        baseMapper.insert(groupDO);
    }

    @Override
    public List<ShortenLinkGroupResponseDTO> listGroup() {
        LambdaQueryWrapper<GroupDO> queryWrapper = Wrappers.lambdaQuery(GroupDO.class)
            .eq(GroupDO::getDeleteFlag, 0)
            .eq(GroupDO::getCreateUsername, UserContext.getUsername())
            .orderByDesc(GroupDO::getSortOrder, GroupDO::getUpdateTime);

        List<GroupDO> groupDOList = baseMapper.selectList(queryWrapper);
        return BeanUtil.copyToList(groupDOList, ShortenLinkGroupResponseDTO.class);
    }

    private boolean isGroupIdRepeated (String groupId) {
        LambdaQueryWrapper<GroupDO> queryWrapper = Wrappers.lambdaQuery(GroupDO.class)
                .eq(GroupDO::getGroupId, groupId)
                .eq(GroupDO::getCreateUsername, UserContext.getUsername())
                .eq(GroupDO::getCreateUsername, null);

        GroupDO hasGroupFlag = baseMapper.selectOne(queryWrapper);

        return hasGroupFlag != null;
    }

    @Override
    public void updateGroup(ShortenLinkGroupUpdateRequestDTO shortenLinkGroupUpdateRequestDTO) {
        LambdaQueryWrapper<GroupDO> updateWrapper = Wrappers.lambdaQuery(GroupDO.class)
                .eq(GroupDO::getCreateUsername, UserContext.getUsername())
                .eq(GroupDO::getGroupId, shortenLinkGroupUpdateRequestDTO.getGroupId())
                .eq(GroupDO::getDeleteFlag, 0);

        GroupDO groupDO = new GroupDO();
        groupDO.setName(shortenLinkGroupUpdateRequestDTO.getName());
        baseMapper.update(groupDO, updateWrapper);
    }

    @Override
    public void deleteGroup(String groupID) {
        LambdaQueryWrapper<GroupDO> deleteWrapper = Wrappers.lambdaQuery(GroupDO.class)
                .eq(GroupDO::getGroupId, groupID)
                .eq(GroupDO::getDeleteFlag, 0);

        if (deleteWrapper != null) {
            GroupDO groupDO = new GroupDO();
            groupDO.setDeleteFlag(1);
            baseMapper.update(groupDO, deleteWrapper);
        }
    }

    @Override
    public void sortGroup(List<ShortenLinkGroupSortRequestDTO> shortenLinkGroupSortRequestDTOList) {
        shortenLinkGroupSortRequestDTOList.forEach(each -> {
            GroupDO groupDO = GroupDO.builder()
                    .groupId(each.getGroupId())
                    .build();

            LambdaUpdateWrapper<GroupDO> updateWrapper = Wrappers.lambdaUpdate(GroupDO.class)
                    .eq(GroupDO::getCreateUsername, UserContext.getUsername())
                    .eq(GroupDO::getGroupId, each.getGroupId())
                    .eq(GroupDO::getDeleteFlag, 0);

            baseMapper.update(groupDO, updateWrapper);
        });
    }
}
