package org.pepello.dto.chat;

import org.pepello.dto.team.DtoTeam;

import java.util.UUID;

public record DtoChat(
        UUID chatId,
        DtoTeam team
) {
}
