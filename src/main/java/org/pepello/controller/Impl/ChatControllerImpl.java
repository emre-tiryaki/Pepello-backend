package org.pepello.controller.Impl;

import org.pepello.common.ICrud;
import org.pepello.common.controller.BaseCrudController;
import org.pepello.common.mapper.BaseMapper;
import org.pepello.controller.IChatController;
import org.pepello.dto.chat.ChatCreateRequest;
import org.pepello.dto.chat.ChatUpdateRequest;
import org.pepello.dto.chat.DtoChat;
import org.pepello.entities.Chat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
public class ChatControllerImpl extends BaseCrudController<Chat, DtoChat, ChatCreateRequest, ChatUpdateRequest> implements IChatController {
    public ChatControllerImpl(ICrud<Chat, ChatCreateRequest, ChatUpdateRequest> service, BaseMapper<Chat, DtoChat> mapper) {
        super(service, mapper);
    }
}
