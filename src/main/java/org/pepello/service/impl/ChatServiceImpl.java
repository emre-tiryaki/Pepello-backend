package org.pepello.service.impl;

import jakarta.transaction.Transactional;
import org.pepello.dto.chat.ChatCreateRequest;
import org.pepello.dto.chat.ChatUpdateRequest;
import org.pepello.entities.Chat;
import org.pepello.entities.Team;
import org.pepello.repository.ChatRepository;
import org.pepello.service.IChatService;
import org.pepello.service.ITeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ChatServiceImpl implements IChatService {
    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private ITeamService teamService;

    @Override
    public List<Chat> getAll() {
        return chatRepository.findAll();
    }

    @Override
    public Chat getById(UUID id) {
        //TODO:hata fırlat canım
        if (id == null)
            return null;

        //TODO:hata fırlat canım
        return chatRepository.findById(id)
                .orElseThrow(null);
    }

    @Override
    public Chat create(ChatCreateRequest createDto) {
        //TODO:hata fırlat
        if (createDto == null)
            return null;

        Chat newChat = Chat.builder()
                .team(teamService.getById(createDto.teamId()))
                .name(createDto.chatName())
                .description(createDto.description())
                .build();

        return chatRepository.save(newChat);
    }

    @Override
    public Chat update(UUID id, ChatUpdateRequest updateDto) {
        //TODO:hata fırlat canım
        if (id == null || updateDto == null)
            return null;

        Chat existingChat = getById(id);

        if (updateDto.teamId() != null) {
            Team existingTeam = teamService.getById(updateDto.teamId());
            existingChat.setTeam(existingTeam);
        }
        if (updateDto.name() != null) existingChat.setName(updateDto.name());
        if (updateDto.description() != null) existingChat.setDescription(updateDto.description());

        return chatRepository.save(existingChat);
    }

    @Override
    public void delete(UUID id) {
        //TODO:hata fırlat canım
        if (id == null)
            return;

        Chat existingChat = getById(id);

        chatRepository.delete(existingChat);
    }

    @Override
    public boolean exists(UUID id) {
        return chatRepository.existsById(id);
    }
}
