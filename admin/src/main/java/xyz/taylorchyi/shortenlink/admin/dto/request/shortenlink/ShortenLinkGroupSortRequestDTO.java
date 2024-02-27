package xyz.taylorchyi.shortenlink.admin.dto.request.shortenlink;

import lombok.Data;

@Data
public class ShortenLinkGroupSortRequestDTO {
    private String groupId;
    private Integer sortOrder;
}