package xyz.taylorchyi.shortenlink.admin.dto.request.shortenlink;

import lombok.Data;

@Data
public class ShorrtenLinkGroupSearchRequestDTO {
    private String groupId;
    private String name;
    private String createUsername;
    private Integer sortOrder;
}
