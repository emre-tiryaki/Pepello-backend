package org.pepello.dto.chat;

import org.pepello.common.request.BaseUpdateRequest;

import java.util.UUID;

public record ChatUpdateRequest(
        UUID teamId,
        UUID iconId,
        String name,
        String description
) implements BaseUpdateRequest {
}
