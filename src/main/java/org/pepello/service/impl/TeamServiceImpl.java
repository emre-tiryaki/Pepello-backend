package org.pepello.service.impl;

import jakarta.transaction.Transactional;
import org.pepello.dto.team.TeamCreateRequest;
import org.pepello.dto.team.TeamUpdateRequest;
import org.pepello.entities.Team;
import org.pepello.entities.User;
import org.pepello.repository.TeamRepository;
import org.pepello.service.ITeamService;
import org.pepello.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class TeamServiceImpl implements ITeamService {
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private IUserService userService;

    @Override
    public List<Team> getAll() {
        return teamRepository.findAll();
    }

    @Override
    public Team getById(UUID id) {
        //TODO: hata fırlat!!!
        if (id == null)
            return null;

        //TODO: hata fırlat!!!
        return teamRepository.findById(id)
                .orElseThrow(null);
    }

    @Override
    public Team create(TeamCreateRequest createDto) {
        //TODO: hata fırlat aq!!!
        if (createDto == null)
            return null;

        Team newTeam = Team.builder()
                .owner(userService.getById(createDto.owner()))
                .teamName(createDto.name())
                .description(createDto.description())
                //TODO: buraya mantık ekle
                .icon(null)
                .build();

        return teamRepository.save(newTeam);
    }

    @Override
    public Team update(UUID id, TeamUpdateRequest updateDto) {
        //TODO: hata fırlat aq!!!
        if (id == null || updateDto == null)
            return null;

        //TODO:hata fırlat!!!
        Team existingTeam = teamRepository.findById(id)
                .orElseThrow(null);

        if (updateDto.owner() != null) {
            //TODO: allah aşkıına hata fırlat!!!!!!!!!
            User ownerOfTeam = userService.getById(updateDto.owner());
            existingTeam.setOwner(ownerOfTeam);
        }
        if (updateDto.name() != null) existingTeam.setTeamName(updateDto.name());
        if (updateDto.description() != null) existingTeam.setDescription(updateDto.description());
        //TODO: buraya mantık ekle
        if (updateDto.icon() != null) existingTeam.setIcon(null);

        return teamRepository.save(existingTeam);
    }

    @Override
    public void delete(UUID id) {
        if (id == null)
            return;

        //TODO:hata fırlat
        Team existingTeam = getById(id);

        teamRepository.delete(existingTeam);
    }

    @Override
    public boolean exists(UUID id) {
        return teamRepository.existsById(id);
    }
}
