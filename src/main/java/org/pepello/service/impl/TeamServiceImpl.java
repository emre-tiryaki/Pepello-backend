package org.pepello.service.impl;

import jakarta.transaction.Transactional;
import org.pepello.common.service.BaseCrudService;
import org.pepello.dto.team.TeamCreateRequest;
import org.pepello.dto.team.TeamUpdateRequest;
import org.pepello.entities.Team;
import org.pepello.service.ITeamService;
import org.pepello.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class TeamServiceImpl extends BaseCrudService<Team, TeamCreateRequest, TeamUpdateRequest> implements ITeamService {
    @Autowired
    private IUserService userService;

    public TeamServiceImpl(JpaRepository<Team, UUID> repository) {
        super(repository);
    }

    @Override
    protected Team buildEntity(TeamCreateRequest createDto) {
        return Team.builder()
                .owner(userService.getById(createDto.owner()))
                .teamName(createDto.name())
                .description(createDto.description())
                .build();
    }

    @Override
    protected void updateEntity(Team existingEntity, TeamUpdateRequest updateDto) {
        if (updateDto.owner() != null)
            existingEntity.setOwner(userService.getById(updateDto.owner()));
        if (updateDto.name() != null)
            existingEntity.setTeamName(updateDto.name());
        if (updateDto.description() != null)
            existingEntity.setDescription(updateDto.description());
    }
}
