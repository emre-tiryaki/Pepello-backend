package org.pepello.dto.chat;

import org.pepello.dto.media.DtoMedia;

import java.util.UUID;

public record ChatUpdateRequest(
        UUID teamId,
        DtoMedia icon,
        String name,
        String description
) {
}
