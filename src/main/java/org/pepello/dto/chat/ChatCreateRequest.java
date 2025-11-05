package org.pepello.dto.chat;

import org.pepello.dto.media.DtoMedia;

import java.util.UUID;

public record ChatCreateRequest(
        UUID teamId,
        DtoMedia icon,
        String chatName,
        String description
) {
}
