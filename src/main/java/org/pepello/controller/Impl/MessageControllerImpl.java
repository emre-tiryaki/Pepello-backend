package org.pepello.controller.Impl;

import org.pepello.controller.IMessageController;
import org.pepello.dto.message.DtoMessage;
import org.pepello.dto.message.MessageCreateRequest;
import org.pepello.dto.message.MessageUpdateRequest;
import org.pepello.mappers.MessageMapper;
import org.pepello.mappers.ProjectMapper;
import org.pepello.service.IMessageService;
import org.pepello.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/message")
public class MessageControllerImpl implements IMessageController {
    @Autowired
    private IMessageService messageService;
    @Autowired
    private MessageMapper messageMapper;

    @Override
    public List<DtoMessage> getAll() {
        return messageService.getAll().stream().map(messageMapper::toDto).toList();
    }

    @Override
    public DtoMessage getById(UUID id) {
        return messageMapper.toDto(messageService.getById(id));
    }

    @Override
    public DtoMessage create(MessageCreateRequest createDto) {
        return messageMapper.toDto(messageService.create(createDto));
    }

    @Override
    public DtoMessage update(UUID id, MessageUpdateRequest updateDto) {
        return messageMapper.toDto(messageService.update(id, updateDto));
    }

    @Override
    public void delete(UUID id) {
        messageService.delete(id);
    }

    @Override
    public boolean exists(UUID id) {
        return messageService.exists(id);
    }
}
