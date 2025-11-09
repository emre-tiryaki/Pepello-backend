package org.pepello.dto.team;

import org.pepello.common.request.BaseUpdateRequest;
import org.pepello.dto.media.DtoMedia;

import java.util.UUID;

public record TeamUpdateRequest(
        UUID owner,
        DtoMedia icon,
        String name,
        String description
) implements BaseUpdateRequest {
}
