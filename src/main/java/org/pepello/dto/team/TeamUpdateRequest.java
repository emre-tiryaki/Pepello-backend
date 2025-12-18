package org.pepello.dto.team;

import org.pepello.common.request.BaseUpdateRequest;

import java.util.UUID;

public record TeamUpdateRequest(
        UUID owner,
        UUID icon,
        String name,
        String description
) implements BaseUpdateRequest {
}
