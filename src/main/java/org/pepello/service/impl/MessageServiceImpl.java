package org.pepello.service.impl;

import jakarta.transaction.Transactional;
import org.pepello.common.service.BaseCrudService;
import org.pepello.dto.message.MessageCreateRequest;
import org.pepello.dto.message.MessageUpdateRequest;
import org.pepello.entities.Message;
import org.pepello.service.IChatService;
import org.pepello.service.IMediaService;
import org.pepello.service.IMessageService;
import org.pepello.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class MessageServiceImpl extends BaseCrudService<Message, MessageCreateRequest, MessageUpdateRequest> implements IMessageService {
    @Autowired
    private IChatService chatService;
    @Autowired
    private IMediaService mediaService;
    @Autowired
    private IUserService userService;

    public MessageServiceImpl(JpaRepository<Message, UUID> repository) {
        super(repository);
    }

    @Override
    protected Message buildEntity(MessageCreateRequest createDto) {
        return Message.builder()
                .user(userService.getById(createDto.userId()))
                .chat(chatService.getById(createDto.chatId()))
                .mediaId(resolveMedia(createDto.mediaId()))
                .text(createDto.text())
                .build();
    }

    @Override
    protected void updateEntity(Message existingEntity, MessageUpdateRequest updateDto) {
        if (updateDto.userId() != null)
            existingEntity.setUser(userService.getById(updateDto.userId()));
        if (updateDto.chatId() != null)
            existingEntity.setChat(chatService.getById(updateDto.chatId()));
        if (updateDto.mediaId() != null)
            existingEntity.setMediaId(resolveMedia(updateDto.mediaId()));
        if (updateDto.text() != null)
            existingEntity.setText(updateDto.text());
    }

    private org.pepello.entities.Media resolveMedia(UUID mediaId) {
        if (mediaId == null) {
            return null;
        }

        try {
            return mediaService.getById(mediaId);
        } catch (Exception e) {
            return null;
        }
    }
}
