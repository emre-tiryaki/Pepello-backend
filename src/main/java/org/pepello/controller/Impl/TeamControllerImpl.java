package org.pepello.controller.Impl;

import org.pepello.common.ICrud;
import org.pepello.common.controller.BaseCrudController;
import org.pepello.common.mapper.BaseMapper;
import org.pepello.controller.ITeamController;
import org.pepello.dto.team.DtoTeam;
import org.pepello.dto.team.TeamCreateRequest;
import org.pepello.dto.team.TeamUpdateRequest;
import org.pepello.entities.Team;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/team")
public class TeamControllerImpl extends BaseCrudController<Team, DtoTeam, TeamCreateRequest, TeamUpdateRequest> implements ITeamController {
    public TeamControllerImpl(ICrud<Team, TeamCreateRequest, TeamUpdateRequest> service, BaseMapper<Team, DtoTeam> mapper) {
        super(service, mapper);
    }
}
