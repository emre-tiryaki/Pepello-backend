package org.pepello.service.impl;

import jakarta.transaction.Transactional;
import org.pepello.common.service.BaseCrudService;
import org.pepello.dto.events.TeamCreatedEvent;
import org.pepello.dto.team.TeamCreateRequest;
import org.pepello.dto.team.TeamUpdateRequest;
import org.pepello.entities.Team;
import org.pepello.entities.User;
import org.pepello.observers.ITeamObserver;
import org.pepello.service.IMediaService;
import org.pepello.service.ITeamService;
import org.pepello.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class TeamServiceImpl extends BaseCrudService<Team, TeamCreateRequest, TeamUpdateRequest> implements ITeamService {
    @Autowired
    private IUserService userService;
    @Autowired
    private IMediaService mediaService;

    private final List<ITeamObserver> observers = new ArrayList<>();

    public TeamServiceImpl(JpaRepository<Team, UUID> repository) {
        super(repository);
    }

    @Override
    public Team create(TeamCreateRequest createDto) {
        Team createdTeam = super.create(createDto);

        notifyObservers(createdTeam, createdTeam.getOwner());

        return createdTeam;
    }

    public void addObserver(ITeamObserver observer){
        this.observers.add(observer);
    }

    public void removeObserver(ITeamObserver observer){
        this.observers.remove(observer);
    }

    private void notifyObservers(Team team, User owner){
        TeamCreatedEvent event = new TeamCreatedEvent(owner ,team);
        for (ITeamObserver observer : observers)
            observer.onTeamCreated(event);
    }

    @Override
    protected Team buildEntity(TeamCreateRequest createDto) {
        return Team.builder()
                .owner(userService.getById(createDto.owner()))
                .teamName(createDto.name())
                .icon(mediaService.getById(createDto.icon()))
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
