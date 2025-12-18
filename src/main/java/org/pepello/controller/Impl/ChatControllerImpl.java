package org.pepello.controller.Impl;

import org.pepello.common.ICrud;
import org.pepello.common.controller.BaseCrudController;
import org.pepello.common.mapper.BaseMapper;
import org.pepello.controller.IChatController;
import org.pepello.dto.chat.ChatCreateRequest;
import org.pepello.dto.chat.ChatUpdateRequest;
import org.pepello.dto.chat.DtoChat;
import org.pepello.dto.message.DtoMessage;
import org.pepello.entities.Chat;
import org.pepello.mappers.MessageMapper;
import org.pepello.service.impl.MessageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/chat")
public class ChatControllerImpl extends BaseCrudController<Chat, DtoChat, ChatCreateRequest, ChatUpdateRequest> implements IChatController {
    @Autowired
    private MessageServiceImpl messageService;
    @Autowired
    private MessageMapper messageMapper;

    public ChatControllerImpl(ICrud<Chat, ChatCreateRequest, ChatUpdateRequest> service, BaseMapper<Chat, DtoChat> mapper) {
        super(service, mapper);

    }

    @GetMapping("/{chatId}/messages")
    @Override
    public List<DtoMessage> getMyChats(
            @PathVariable UUID chatId
    ) {
        return messageService.getChatMessages(chatId)
                .stream()
                .map(messageMapper::toDto)
                .toList();
    }
}
