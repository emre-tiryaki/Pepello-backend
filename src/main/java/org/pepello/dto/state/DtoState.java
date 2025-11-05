package org.pepello.dto.state;

import org.pepello.dto.media.DtoMedia;
import org.pepello.entities.enums.Color;

import java.util.UUID;

public record DtoState(
        UUID id,
        String stateName,
        DtoMedia icon,
        Color stateColor
) {
}
