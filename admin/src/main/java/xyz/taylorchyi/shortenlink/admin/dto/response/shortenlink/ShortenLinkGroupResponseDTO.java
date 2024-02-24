package xyz.taylorchyi.shortenlink.admin.dto.response.shortenlink;

import lombok.Data;

@Data
public class ShortenLinkGroupResponseDTO {
    private String groupId;
    private String name;
    private String createUsername;
    private Integer sortOrder;
}
