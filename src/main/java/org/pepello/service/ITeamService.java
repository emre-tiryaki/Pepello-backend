package org.pepello.service;

import org.pepello.dto.team.TeamCreateRequest;
import org.pepello.dto.team.TeamUpdateRequest;
import org.pepello.entities.Team;

public interface ITeamService extends ICrudService<Team, TeamCreateRequest, TeamUpdateRequest> {
}
