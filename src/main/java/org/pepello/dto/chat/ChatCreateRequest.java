package org.pepello.dto.chat;

import org.pepello.common.request.BaseCreateRequest;

import java.util.UUID;

public record ChatCreateRequest(
        UUID teamId,
        UUID iconId,
        String chatName,
        String description
) implements BaseCreateRequest {
}
