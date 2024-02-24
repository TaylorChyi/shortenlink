package xyz.taylorchyi.shortenlink.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import xyz.taylorchyi.shortenlink.admin.common.convention.result.Result;
import xyz.taylorchyi.shortenlink.admin.common.convention.result.Results;
import xyz.taylorchyi.shortenlink.admin.dto.request.shortenlink.ShortenLinkGroupSaveRequestDTO;
import xyz.taylorchyi.shortenlink.admin.dto.response.shortenlink.ShortenLinkGroupResponseDTO;
import xyz.taylorchyi.shortenlink.admin.service.GroupService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @PostMapping("/api/shorten-link/v1/group")
    public Result<Void> saveGroup(@RequestBody ShortenLinkGroupSaveRequestDTO shortenLinkGroupSaveRequestDTO) {
        groupService.saveGroup(shortenLinkGroupSaveRequestDTO.getName());
        return Results.success();
    }

    @GetMapping("/api/shorten-link/v1/group")
    public Result<List<ShortenLinkGroupResponseDTO>> listGroup() {
        List<ShortenLinkGroupResponseDTO> result = groupService.listGroup();
        return Results.success(result);
    }
}
