package org.pepello.dto.message;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import org.antlr.v4.runtime.misc.NotNull;
import org.pepello.dto.chat.DtoChat;
import org.pepello.dto.media.DtoMedia;
import org.pepello.dto.user.DtoUser;

import java.util.UUID;

public record DtoMessage(
        UUID messageId,
        DtoUser user,
        DtoChat chat,
        String text,
        DtoMedia media
) {
}
