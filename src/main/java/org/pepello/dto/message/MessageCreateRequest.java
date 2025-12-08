package org.pepello.dto.message;

import org.pepello.common.request.BaseCreateRequest;

import java.util.UUID;

public record MessageCreateRequest(
        UUID userId,
        UUID chatId,
        String text,
        UUID mediaId
) implements BaseCreateRequest {
}

