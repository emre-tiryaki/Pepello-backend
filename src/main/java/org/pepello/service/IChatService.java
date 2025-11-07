package org.pepello.service;

import org.pepello.dto.chat.ChatCreateRequest;
import org.pepello.dto.chat.ChatUpdateRequest;
import org.pepello.entities.Chat;
import org.pepello.common.ICrud;

public interface IChatService extends ICrud<Chat, ChatCreateRequest, ChatUpdateRequest> {
}
