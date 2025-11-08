package org.pepello.service;

import org.pepello.common.service.RelationMethods;
import org.pepello.entities.Project;
import org.pepello.entities.ProjectStateRelation;
import org.pepello.entities.State;

public interface IProjectStateRelationService extends RelationMethods<Project, State, ProjectStateRelation> {
}
