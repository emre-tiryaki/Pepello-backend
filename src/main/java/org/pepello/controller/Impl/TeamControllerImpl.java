package org.pepello.controller.Impl;

import org.pepello.controller.ITeamController;
import org.pepello.dto.team.DtoTeam;
import org.pepello.dto.team.TeamCreateRequest;
import org.pepello.dto.team.TeamUpdateRequest;
import org.pepello.mappers.TeamMapper;
import org.pepello.service.ITeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/team")
public class TeamControllerImpl implements ITeamController {
    @Autowired
    private ITeamService teamService;
    @Autowired
    private TeamMapper teamMapper;

    @Override
    public List<DtoTeam> getAll() {
        return teamService.getAll().stream()
                .map(teamMapper::toDto)
                .toList();
    }

    @Override
    public DtoTeam getById(UUID id) {
        return teamMapper.toDto(teamService.getById(id));
    }

    @Override
    public DtoTeam create(TeamCreateRequest createDto) {
        return teamMapper.toDto(teamService.create(createDto));
    }

    @Override
    public DtoTeam update(UUID id, TeamUpdateRequest updateDto) {
        return teamMapper.toDto(teamService.update(id, updateDto));
    }

    @Override
    public void delete(UUID id) {
        teamService.delete(id);
    }

    @Override
    public boolean exists(UUID id) {
        return teamService.exists(id);
    }
}
