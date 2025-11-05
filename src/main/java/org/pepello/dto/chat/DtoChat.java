package org.pepello.dto.chat;

import org.pepello.dto.team.DtoTeam;

import java.util.UUID;

public record DtoChat(
        UUID id,
        DtoTeam team
) {
}
