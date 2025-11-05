package org.pepello.dto.media;

import org.pepello.entities.enums.MediaType;

import java.math.BigDecimal;
import java.util.UUID;

public record DtoMedia(
        UUID id,
        MediaType mediaType,
        BigDecimal mediaSize,
        String mediaUrl
) {
}
