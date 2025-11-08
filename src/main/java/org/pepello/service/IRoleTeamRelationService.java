package org.pepello.service;

import org.pepello.common.service.RelationMethods;
import org.pepello.entities.Role;
import org.pepello.entities.RoleTeamRelation;
import org.pepello.entities.Team;

public interface IRoleTeamRelationService extends RelationMethods<Role, Team, RoleTeamRelation> {
}
