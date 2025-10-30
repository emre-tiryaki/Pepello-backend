package org.pepello.dto.team;

import org.pepello.dto.media.DtoMedia;
import org.pepello.dto.user.DtoUser;

import java.util.UUID;

public record DtoTeam(
        UUID teamId,
        DtoUser owner,
        DtoMedia icon,
        String teamName,
        String description
) {
}
