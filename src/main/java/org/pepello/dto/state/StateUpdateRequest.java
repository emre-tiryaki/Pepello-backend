package org.pepello.dto.state;

import org.pepello.common.request.BaseUpdateRequest;
import org.pepello.dto.media.DtoMedia;
import org.pepello.entities.enums.Color;

public record StateUpdateRequest(
        String stateName,
        DtoMedia icon,
        Color stateColor
) implements BaseUpdateRequest {
}
