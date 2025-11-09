package org.pepello.service;

import org.pepello.common.ICrud;
import org.pepello.dto.chat.ChatCreateRequest;
import org.pepello.dto.chat.ChatUpdateRequest;
import org.pepello.entities.Chat;

public interface IChatService extends ICrud<Chat, ChatCreateRequest, ChatUpdateRequest> {
}
