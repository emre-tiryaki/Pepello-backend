package org.pepello.service;

import org.pepello.common.repository.BaseRelationRepository;
import org.pepello.common.service.RelationMethods;
import org.pepello.entities.Task;
import org.pepello.entities.TaskAsigneeRelation;
import org.pepello.entities.User;

public interface ITaskAsigneeRelationService extends RelationMethods<Task, User, TaskAsigneeRelation> {
}
