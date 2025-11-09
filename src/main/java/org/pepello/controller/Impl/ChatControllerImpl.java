package org.pepello.controller.Impl;

import org.pepello.controller.IChatController;
import org.pepello.dto.chat.ChatCreateRequest;
import org.pepello.dto.chat.ChatUpdateRequest;
import org.pepello.dto.chat.DtoChat;
import org.pepello.mappers.ChatMapper;
import org.pepello.service.IChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/chat")
public class ChatControllerImpl implements IChatController {
    @Autowired
    private IChatService chatService;
    @Autowired
    private ChatMapper chatMapper;

    @Override
    public List<DtoChat> getAll() {
        return chatService.getAll().stream()
                .map(chatMapper::toDto)
                .toList();
    }

    @Override
    public DtoChat getById(UUID id) {
        return chatMapper.toDto(chatService.getById(id));
    }

    @Override
    public DtoChat create(ChatCreateRequest createDto) {
        return chatMapper.toDto(chatService.create(createDto));
    }

    @Override
    public DtoChat update(UUID id, ChatUpdateRequest updateDto) {
        return chatMapper.toDto(chatService.update(id, updateDto));
    }

    @Override
    public void delete(UUID id) {
        chatService.delete(id);
    }

    @Override
    public boolean exists(UUID id) {
        return chatService.exists(id);
    }
}
