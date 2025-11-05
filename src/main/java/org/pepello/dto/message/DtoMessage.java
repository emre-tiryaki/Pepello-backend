package org.pepello.dto.message;

import org.pepello.dto.chat.DtoChat;
import org.pepello.dto.media.DtoMedia;
import org.pepello.dto.user.DtoUser;

import java.util.UUID;

public record DtoMessage(
        UUID id,
        DtoUser user,
        DtoChat chat,
        String text,
        DtoMedia media
) {
}
