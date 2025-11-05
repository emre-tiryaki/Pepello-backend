package org.pepello.dto.media;

import org.pepello.entities.enums.MediaType;

import java.math.BigDecimal;

public record MediaCreateRequest(
        MediaType mediaType,
        BigDecimal mediaSize,
        String mediaUrl
) {
}
