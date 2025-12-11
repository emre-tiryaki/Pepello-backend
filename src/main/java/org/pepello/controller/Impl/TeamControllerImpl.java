package org.pepello.controller.Impl;

import org.pepello.common.ICrud;
import org.pepello.common.controller.BaseCrudController;
import org.pepello.common.mapper.BaseMapper;
import org.pepello.common.service.BaseRelationService;
import org.pepello.controller.ITeamController;
import org.pepello.dto.relations.DtoRelation;
import org.pepello.dto.relations.RelationCreateRequest;
import org.pepello.dto.team.DtoTeam;
import org.pepello.dto.team.TeamCreateRequest;
import org.pepello.dto.team.TeamUpdateRequest;
import org.pepello.dto.user.DtoUser;
import org.pepello.entities.Team;
import org.pepello.entities.User;
import org.pepello.entities.UserTeamRelation;
import org.pepello.mappers.UserMapper;
import org.pepello.service.ITeamService;
import org.pepello.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/team")
public class TeamControllerImpl extends BaseCrudController<Team, DtoTeam, TeamCreateRequest, TeamUpdateRequest> implements ITeamController {

    @Autowired
    private IUserService userService;
    @Autowired
    private UserMapper userMapper;
    private final BaseRelationService<User, Team, UserTeamRelation> userTeamRelationService;

    public TeamControllerImpl(
            ICrud<Team, TeamCreateRequest, TeamUpdateRequest> service,
            BaseMapper<Team, DtoTeam> mapper,
            BaseRelationService<User, Team, UserTeamRelation> userTeamRelationService
    ) {
        super(service, mapper);
        this.userTeamRelationService = userTeamRelationService;
    }

    @GetMapping("/{teamId}/members")
    public List<DtoUser> getMembers(
            @PathVariable UUID teamId
    ) {
        return userTeamRelationService.getPrimaryEntities(teamId)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @PostMapping("/{teamId}/add-member")
    @ResponseStatus(HttpStatus.CREATED)
    public DtoRelation<DtoUser, DtoTeam> addMember(
            @PathVariable UUID teamId,
            @RequestBody RelationCreateRequest request
            ){
        UUID userId = request.id();

        userTeamRelationService.addRelation(userId, teamId);

        return new DtoRelation<>(userMapper.toDto(userService.getById(userId)), getById(request.id()));
    }

}
