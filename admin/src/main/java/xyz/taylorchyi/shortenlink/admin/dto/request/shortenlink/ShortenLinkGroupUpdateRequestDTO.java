package xyz.taylorchyi.shortenlink.admin.dto.request.shortenlink;

import lombok.Data;

@Data
public class ShortenLinkGroupUpdateRequestDTO {
    private String groupId;
    private String name;
}
