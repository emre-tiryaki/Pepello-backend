package org.pepello.service;

import org.pepello.common.ICrud;
import org.pepello.dto.chat.ChatCreateRequest;
import org.pepello.dto.chat.ChatUpdateRequest;
import org.pepello.entities.Chat;
import org.pepello.entities.Media;

import java.util.UUID;

public interface IChatService extends ICrud<Chat, ChatCreateRequest, ChatUpdateRequest> {
    Media resolveIcon(UUID iconId);
}
