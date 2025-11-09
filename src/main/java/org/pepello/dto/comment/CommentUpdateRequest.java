package org.pepello.dto.comment;

import org.pepello.common.request.BaseUpdateRequest;

import java.util.UUID;

public record CommentUpdateRequest(
        UUID taskId,
        UUID userId,
        String text
) implements BaseUpdateRequest {
}
