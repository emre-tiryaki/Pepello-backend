package org.pepello.controller;

import org.pepello.common.controller.ICrudEndpoints;
import org.pepello.dto.chat.ChatCreateRequest;
import org.pepello.dto.chat.ChatUpdateRequest;
import org.pepello.dto.chat.DtoChat;

public interface IChatController extends ICrudEndpoints<DtoChat, ChatCreateRequest, ChatUpdateRequest> {
}
