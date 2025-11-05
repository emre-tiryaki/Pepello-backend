package org.pepello.dto.comment;

import java.util.UUID;

public record CommentUpdateRequest(
        UUID taskId,
        UUID userId,
        String text
) {
}
