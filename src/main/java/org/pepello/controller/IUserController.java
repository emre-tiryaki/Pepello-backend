package org.pepello.controller;

import org.pepello.dto.task.DtoTask;
import org.pepello.dto.team.DtoTeam;
import org.pepello.dto.user.DtoUser;
import org.pepello.dto.user.UserSearchRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

public interface IUserController {
    @GetMapping("/me")
    DtoUser getMe(
            @RequestAttribute("userId") UUID userId
    );

    @GetMapping("/{userId}/teams")
    List<DtoTeam> getUsersTeams(
            @PathVariable UUID userId
    );

    @GetMapping("/{userId}/tasks")
    List<DtoTask> getUsersTasks(
            @PathVariable UUID userId
    );

    @GetMapping("/search")
    List<DtoUser> searchUser(
            @RequestBody UserSearchRequest request
    );
}
