package org.pepello.service.impl;

import jakarta.transaction.Transactional;
import org.pepello.dto.message.MessageCreateRequest;
import org.pepello.dto.message.MessageUpdateRequest;
import org.pepello.entities.Message;
import org.pepello.repository.MessageRepository;
import org.pepello.service.IChatService;
import org.pepello.service.IMediaService;
import org.pepello.service.IMessageService;
import org.pepello.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class MessageServiceImpl implements IMessageService {
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private IChatService chatService;
    @Autowired
    private IMediaService mediaService;
    @Autowired
    private IUserService userService;

    @Override
    public List<Message> getAll() {
        return messageRepository.findAll();
    }

    @Override
    public Message getById(UUID id) {
        if (id == null)
            throw new RuntimeException("aaaa");

        return messageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("aaaaa"));
    }

    @Override
    public Message create(MessageCreateRequest createDto) {
        if (createDto == null)
            throw new RuntimeException("aaaa");

        Message newMessage = Message.builder()
                .user(userService.getById(createDto.userId()))
                .chat(chatService.getById(createDto.chatId()))
                .text(createDto.text())
                .mediaId(mediaService.getById(createDto.mediaId()))
                .build();

        return messageRepository.save(newMessage);
    }

    @Override
    public Message update(UUID id, MessageUpdateRequest updateDto) {
        // TODO: Daha iyi bir hata mimarisi yapınca değiştirilecek
        if (id == null) {
            throw new RuntimeException("ID null olamaz");
        }
        if (updateDto == null) {
            throw new RuntimeException("UpdateDto null olamaz");
        }

        Message existingMessage = getById(id);

        if (updateDto.userId() != null) existingMessage.setUser(userService.getById(updateDto.userId()));
        if (updateDto.chatId() != null) existingMessage.setChat(chatService.getById(updateDto.chatId()));
        if (updateDto.text() != null) existingMessage.setText(updateDto.text());
        if (updateDto.mediaId() != null) existingMessage.setMediaId(mediaService.getById(updateDto.mediaId()));

        return messageRepository.save(existingMessage);
    }

    @Override
    public void delete(UUID id) {
        if(id == null)
            throw new RuntimeException("aadawda");

        Message existingMessage = getById(id);

        messageRepository.delete(existingMessage);
    }

    @Override
    public boolean exists(UUID id) {
        return messageRepository.existsById(id);
    }
}
