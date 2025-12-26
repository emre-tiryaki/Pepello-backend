package org.pepello.controller;

import org.pepello.dto.project.DtoProject;
import org.pepello.dto.relations.DtoRelation;
import org.pepello.dto.relations.RelationCreateRequest;
import org.pepello.dto.team.DtoTeam;
import org.pepello.dto.team.TeamAddMemberByEmailRequest;
import org.pepello.dto.user.DtoUser;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

public interface ITeamController {
        @GetMapping("/{teamId}/members")
        List<DtoUser> getMembers(
                        @PathVariable UUID teamId);

        @PostMapping("/{teamId}/add-member")
        @ResponseStatus(HttpStatus.CREATED)
        DtoRelation<DtoUser, DtoTeam> addMember(
                        @PathVariable UUID teamId,
                        @RequestBody RelationCreateRequest request);

        @PostMapping("/{teamId}/add-member-by-email")
        @ResponseStatus(HttpStatus.CREATED)
        DtoRelation<DtoUser, DtoTeam> addMemberByEmail(
                        @PathVariable UUID teamId,
                        @RequestBody TeamAddMemberByEmailRequest request);

        @GetMapping("/{teamId}/projects")
        List<DtoProject> getProjects(
                        @PathVariable UUID teamId);

        @DeleteMapping("/{teamId}/members/{userId}")
        void deleteUserFromTeam(
                        @PathVariable UUID teamId,
                        @PathVariable UUID userId);
}
