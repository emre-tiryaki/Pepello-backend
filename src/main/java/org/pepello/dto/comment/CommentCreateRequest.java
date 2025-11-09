package org.pepello.dto.comment;

import org.pepello.common.request.BaseCreateRequest;

import java.util.UUID;

public record CommentCreateRequest(
        UUID taskId,
        UUID userId,
        String text
) implements BaseCreateRequest {
}
