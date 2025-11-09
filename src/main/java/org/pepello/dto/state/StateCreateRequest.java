package org.pepello.dto.state;

import org.pepello.common.request.BaseCreateRequest;
import org.pepello.dto.media.DtoMedia;
import org.pepello.entities.enums.Color;

public record StateCreateRequest(
        String stateName,
        DtoMedia icon,
        Color stateColor
) implements BaseCreateRequest {
}
