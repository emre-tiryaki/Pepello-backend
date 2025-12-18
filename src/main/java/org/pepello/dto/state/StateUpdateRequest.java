package org.pepello.dto.state;

import org.pepello.common.request.BaseUpdateRequest;
import org.pepello.entities.enums.Color;

import java.util.UUID;

public record StateUpdateRequest(
        String stateName,
        UUID icon,
        Color stateColor
) implements BaseUpdateRequest {
}
