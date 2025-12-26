package org.pepello.controller.Impl;

import jakarta.validation.Valid;
import org.pepello.common.ICrud;
import org.pepello.common.controller.BaseCrudController;
import org.pepello.common.mapper.BaseMapper;
import org.pepello.controller.ITeamController;
import org.pepello.dto.project.DtoProject;
import org.pepello.dto.relations.DtoRelation;
import org.pepello.dto.relations.RelationCreateRequest;
import org.pepello.dto.team.DtoTeam;
import org.pepello.dto.team.TeamAddMemberByEmailRequest;
import org.pepello.dto.team.TeamCreateRequest;
import org.pepello.dto.team.TeamUpdateRequest;
import org.pepello.dto.user.DtoUser;
import org.pepello.entities.Team;
import org.pepello.entities.User;
import org.pepello.mappers.ProjectMapper;
import org.pepello.mappers.UserMapper;
import org.pepello.service.IUserService;
import org.pepello.service.impl.TeamProjectRelationServiceImpl;
import org.pepello.service.impl.UserTeamRelationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/team")
public class TeamControllerImpl extends BaseCrudController<Team, DtoTeam, TeamCreateRequest, TeamUpdateRequest>
                implements ITeamController {

        @Autowired
        private IUserService userService;
        @Autowired
        private UserMapper userMapper;
        @Autowired
        private UserTeamRelationServiceImpl userTeamRelationService;
        @Autowired
        private TeamProjectRelationServiceImpl teamProjectRelationService;
        @Autowired
        private ProjectMapper projectMapper;

        public TeamControllerImpl(
                        ICrud<Team, TeamCreateRequest, TeamUpdateRequest> service,
                        BaseMapper<Team, DtoTeam> mapper) {
                super(service, mapper);
        }

        @GetMapping("/{teamId}/members")
        @Override
        public List<DtoUser> getMembers(
                        @PathVariable UUID teamId) {
                return userTeamRelationService.getPrimaryEntities(teamId)
                                .stream()
                                .map(userMapper::toDto)
                                .toList();
        }

        @PostMapping("/{teamId}/add-member")
        @ResponseStatus(HttpStatus.CREATED)
        @Override
        public DtoRelation<DtoUser, DtoTeam> addMember(
                        @PathVariable UUID teamId,
                        @Valid @RequestBody RelationCreateRequest request) {
                UUID userId = request.id();

                userTeamRelationService.addRelation(userId, teamId);

                return new DtoRelation<>(userMapper.toDto(userService.getById(userId)), getById(request.id()));
        }

        @PostMapping("/{teamId}/add-member-by-email")
        @ResponseStatus(HttpStatus.CREATED)
        @Override
        public DtoRelation<DtoUser, DtoTeam> addMemberByEmail(
                        @PathVariable UUID teamId,
                        @Valid @RequestBody TeamAddMemberByEmailRequest request) {
                User user = userService.getByEmail(request.email());
                UUID userId = user.getId();

                userTeamRelationService.addRelation(userId, teamId);

                return new DtoRelation<>(userMapper.toDto(user), getById(teamId));
        }

        @GetMapping("/{teamId}/projects")
        @Override
        public List<DtoProject> getProjects(
                        @PathVariable UUID teamId) {
                return teamProjectRelationService.getRelatedEntities(teamId)
                                .stream()
                                .map(projectMapper::toDto)
                                .toList();
        }

        @DeleteMapping("/{teamId}/members/{userId}")
        @Override
        public void deleteUserFromTeam(
                        @PathVariable UUID teamId,
                        @PathVariable UUID userId) {
                userTeamRelationService.removeRelation(userId, teamId);
        }

}
