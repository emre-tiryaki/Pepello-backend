package org.pepello.dto.message;

import org.pepello.common.request.BaseUpdateRequest;

import java.util.UUID;

public record MessageUpdateRequest(
        UUID userId,
        UUID chatId,
        String text,
        UUID mediaId
) implements BaseUpdateRequest {
}


