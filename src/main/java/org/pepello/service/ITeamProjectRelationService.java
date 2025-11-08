package org.pepello.service;

import org.pepello.common.service.RelationMethods;
import org.pepello.entities.Project;
import org.pepello.entities.Team;
import org.pepello.entities.TeamProjectRelation;

public interface ITeamProjectRelationService extends RelationMethods<Team, Project, TeamProjectRelation> {
}
