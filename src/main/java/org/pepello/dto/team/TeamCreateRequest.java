package org.pepello.dto.team;

import org.pepello.dto.media.DtoMedia;

import java.util.UUID;

public record TeamCreateRequest(
        UUID owner,
        DtoMedia icon,
        String name,
        String description
) {
}
