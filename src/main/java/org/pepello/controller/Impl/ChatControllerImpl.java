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
import org.pepello.mappers.ChatMapper;
import org.pepello.mappers.MessageMapper;
import org.pepello.service.impl.ChatServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/chat")
public class ChatControllerImpl extends BaseCrudController<Chat, DtoChat, ChatCreateRequest, ChatUpdateRequest> implements IChatController {
    private final ChatServiceImpl chatService;
    @Autowired
    private MessageMapper messageMapper;
    public ChatControllerImpl(ICrud<Chat, ChatCreateRequest, ChatUpdateRequest> service, BaseMapper<Chat, DtoChat> mapper) {
        super(service, mapper);
        this.chatService = (ChatServiceImpl) service;

    }

    @GetMapping("/{chatId}/messages")
    public List<DtoMessage> getMyChats(
            @PathVariable UUID chatId
    ) {
        return chatService.getMessages(chatId)
                .stream()
                .map(messageMapper::toDto)
                .toList();
    }
}
