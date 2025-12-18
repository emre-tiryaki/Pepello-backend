package org.pepello.controller.Impl;

import jakarta.validation.Valid;
import org.pepello.common.ICrud;
import org.pepello.common.controller.BaseCrudController;
import org.pepello.common.mapper.BaseMapper;
import org.pepello.controller.IUserController;
import org.pepello.dto.task.DtoTask;
import org.pepello.dto.team.DtoTeam;
import org.pepello.dto.user.DtoUser;
import org.pepello.dto.user.UserCreateRequest;
import org.pepello.dto.user.UserSearchRequest;
import org.pepello.dto.user.UserUpdateRequest;
import org.pepello.entities.User;
import org.pepello.mappers.TaskMapper;
import org.pepello.mappers.TeamMapper;
import org.pepello.mappers.UserMapper;
import org.pepello.service.impl.TaskAsigneeRelationServiceImpl;
import org.pepello.service.impl.UserServiceImpl;
import org.pepello.service.impl.UserTeamRelationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserControllerImpl extends BaseCrudController<User, DtoUser, UserCreateRequest, UserUpdateRequest> implements IUserController {
    @Autowired
    private UserTeamRelationServiceImpl userTeamRelationService;
    @Autowired
    private TeamMapper teamMapper;
    @Autowired
    private TaskAsigneeRelationServiceImpl taskAsigneeRelationService;
    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private UserMapper userMapper;
    private final UserServiceImpl userService;

    public UserControllerImpl(ICrud<User, UserCreateRequest, UserUpdateRequest> service, BaseMapper<User, DtoUser> mapper) {
        super(service, mapper);
        this.userService = (UserServiceImpl) service;
    }

    @GetMapping("/me")
    @Override
    public DtoUser getMe(
            @RequestAttribute("userId") UUID userId
    ) {
        User user = service.getById(userId);

        return mapper.toDto(user);
    }

    @GetMapping("/{userId}/teams")
    @Override
    public List<DtoTeam> getUsersTeams(
            @PathVariable UUID userId
    ) {
        return userTeamRelationService
                .getRelatedEntities(userId)
                .stream()
                .map(teamMapper::toDto)
                .toList();
    }

    @GetMapping("/{userId}/tasks")
    @Override
    public List<DtoTask> getUsersTasks(
            @PathVariable UUID userId
    ) {
        return taskAsigneeRelationService
                .getPrimaryEntities(userId)
                .stream()
                .map(taskMapper::toDto)
                .toList();
    }

    @GetMapping("/search")
    @Override
    public List<DtoUser> searchUser(
            @Valid @RequestBody UserSearchRequest request
    ) {
        return userService.searchUser(request)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }
}
