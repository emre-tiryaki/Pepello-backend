package org.pepello.dto.comment;

import java.util.UUID;

public record CommentCreateRequest(
        UUID taskId,
        UUID userId,
        String text
) {
}
