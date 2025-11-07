package org.pepello.controller;

import org.pepello.common.controller.ICrudEndpoints;
import org.pepello.dto.team.DtoTeam;
import org.pepello.dto.team.TeamCreateRequest;
import org.pepello.dto.team.TeamUpdateRequest;

public interface ITeamController extends ICrudEndpoints<DtoTeam, TeamCreateRequest, TeamUpdateRequest> {
}
