package org.pepello.service;

import org.pepello.common.service.RelationMethods;
import org.pepello.entities.Team;
import org.pepello.entities.User;
import org.pepello.entities.UserTeamRelation;

public interface IUserTeamRelationService extends RelationMethods<User, Team, UserTeamRelation> {
}
