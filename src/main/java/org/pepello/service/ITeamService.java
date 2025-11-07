package org.pepello.service;

import org.pepello.dto.team.TeamCreateRequest;
import org.pepello.dto.team.TeamUpdateRequest;
import org.pepello.entities.Team;
import org.pepello.common.ICrud;

public interface ITeamService extends ICrud<Team, TeamCreateRequest, TeamUpdateRequest> {
}
