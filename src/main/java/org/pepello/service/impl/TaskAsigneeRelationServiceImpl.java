package org.pepello.service.impl;

import jakarta.transaction.Transactional;
import org.pepello.common.ICrud;
import org.pepello.common.repository.BaseRelationRepository;
import org.pepello.common.service.BaseRelationService;
import org.pepello.entities.Task;
import org.pepello.entities.TaskAsigneeRelation;
import org.pepello.entities.User;
import org.pepello.repository.TaskAsigneeRelationRepository;
import org.pepello.service.ITaskAsigneeRelationService;
import org.pepello.service.ITaskService;
import org.pepello.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class TaskAsigneeRelationServiceImpl extends BaseRelationService<Task, User, TaskAsigneeRelation> implements ITaskAsigneeRelationService {
    @Autowired
    private TaskAsigneeRelationRepository taskAsigneeRelationRepository;
    @Autowired
    private ITaskService taskService;
    @Autowired
    private IUserService userService;


    @Override
    protected BaseRelationRepository<TaskAsigneeRelation> getRepository() {
        return taskAsigneeRelationRepository;
    }

    @Override
    protected ICrud<Task, ?, ?> getPrimaryService() {
        return taskService;
    }

    @Override
    protected ICrud<User, ?, ?> getRelatedService() {
        return userService;
    }

    @Override
    protected TaskAsigneeRelation buildRelation(Task task, User user) {
        return TaskAsigneeRelation.builder()
                .task(task)
                .user(user)
                .build();
    }

    @Override
    protected java.util.Optional<TaskAsigneeRelation> findRelationOptional(UUID primaryId, UUID relatedId) {
        return taskAsigneeRelationRepository.findByTask_IdAndUser_Id(primaryId, relatedId);
    }

    @Override
    protected List<TaskAsigneeRelation> findByPrimaryId(UUID primaryId) {
        return taskAsigneeRelationRepository.findByTask_Id(primaryId);
    }

    @Override
    protected List<TaskAsigneeRelation> findByRelatedId(UUID relatedId) {
        return taskAsigneeRelationRepository.findByUser_Id(relatedId);
    }

    @Override
    protected void deleteAllByPrimaryId(UUID primaryId) {
        taskAsigneeRelationRepository.deleteAllByTask_Id(primaryId);
    }

    @Override
    protected void deleteAllByRelatedId(UUID relatedId) {
        taskAsigneeRelationRepository.deleteAllByUser_Id(relatedId);
    }

    @Override
    protected boolean existsRelation(UUID primaryId, UUID relatedId) {
        return taskAsigneeRelationRepository.existsByTask_IdAndUser_Id(primaryId, relatedId);
    }

    @Override
    protected long countByPrimaryId(UUID primaryId) {
        return taskAsigneeRelationRepository.countByTask_Id(primaryId);
    }

    @Override
    protected long countByRelatedId(UUID relatedId) {
        return taskAsigneeRelationRepository.countByUser_Id(relatedId);
    }

    @Override
    protected Task extractPrimary(TaskAsigneeRelation taskAsigneeRelation) {
        return taskAsigneeRelation.getTask();
    }

    @Override
    protected User extractRelated(TaskAsigneeRelation taskAsigneeRelation) {
        return taskAsigneeRelation.getUser();
    }
}
