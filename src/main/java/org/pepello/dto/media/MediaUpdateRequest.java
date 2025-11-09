package org.pepello.dto.media;

import org.pepello.common.request.BaseUpdateRequest;
import org.pepello.entities.enums.MediaType;

import java.math.BigDecimal;

public record MediaUpdateRequest(
        MediaType mediaType,
        BigDecimal mediaSize,
        String mediaUrl
) implements BaseUpdateRequest {
}
