package org.pepello.dto.team;

import org.pepello.common.request.BaseCreateRequest;

import java.util.UUID;

public record TeamCreateRequest(
        UUID owner,
        UUID icon,
        String name,
        String description
) implements BaseCreateRequest {
}
