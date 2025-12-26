package org.pepello.dto.state;

import org.pepello.common.request.BaseCreateRequest;
import org.pepello.entities.enums.Color;

import java.util.UUID;

public record StateCreateRequest(
                String stateName,
                UUID icon,
                Color stateColor,
                UUID projectId
        ) implements BaseCreateRequest {
}
