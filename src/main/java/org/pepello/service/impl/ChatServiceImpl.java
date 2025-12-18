package org.pepello.service.impl;

import jakarta.transaction.Transactional;
import org.pepello.common.service.BaseCrudService;
import org.pepello.dto.chat.ChatCreateRequest;
import org.pepello.dto.chat.ChatUpdateRequest;
import org.pepello.entities.Chat;
import org.pepello.entities.Media;
import org.pepello.service.IChatService;
import org.pepello.service.IMediaService;
import org.pepello.service.ITeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class ChatServiceImpl extends BaseCrudService<Chat, ChatCreateRequest, ChatUpdateRequest> implements IChatService {
    @Autowired
    private ITeamService teamService;
    @Autowired
    private IMediaService mediaService;

    public ChatServiceImpl(JpaRepository<Chat, UUID> repository) {
        super(repository);
    }

    @Override
    protected Chat buildEntity(ChatCreateRequest createDto) {
        return Chat.builder()
                .team(teamService.getById(createDto.teamId()))
                .icon(resolveIcon(createDto.iconId()))
                .name(createDto.chatName())
                .description(createDto.description())
                .build();
    }

    @Override
    protected void updateEntity(Chat existingEntity, ChatUpdateRequest updateDto) {
        if (updateDto.teamId() != null)
            existingEntity.setTeam(teamService.getById(updateDto.teamId()));
        if (updateDto.iconId() != null)
            existingEntity.setIcon(resolveIcon(updateDto.iconId()));
        if (updateDto.name() != null)
            existingEntity.setName(updateDto.name());
        if (updateDto.description() != null)
            existingEntity.setDescription(updateDto.description());
    }

    @Override
    public Media resolveIcon(UUID iconId) {
        if (iconId == null) {
            return null;
        }

        try {
            return mediaService.getById(iconId);
        } catch (Exception e) {
            // Icon bulunamazsa null döner, sistem çökmez
            return null;
        }
    }
}
