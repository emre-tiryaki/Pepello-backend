package org.pepello.service;

import org.pepello.dto.chat.ChatCreateRequest;
import org.pepello.dto.chat.ChatUpdateRequest;
import org.pepello.entities.Chat;

public interface IChatService extends ICrudService<Chat, ChatCreateRequest, ChatUpdateRequest> {
}
