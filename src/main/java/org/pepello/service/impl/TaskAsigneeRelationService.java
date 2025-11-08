package org.pepello.service.impl;

import jakarta.transaction.Transactional;
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
public class TaskAsigneeRelationService implements ITaskAsigneeRelationService {
    @Autowired
    private TaskAsigneeRelationRepository taskAsigneeRelationRepository;
    @Autowired
    private ITaskService taskService;
    @Autowired
    private IUserService userService;

    @Override
    public TaskAsigneeRelation addRelation(UUID primaryId, UUID relatedId) {
        //TODO: hata fırlat burda!!!
        if (primaryId == null || relatedId == null)
            return null;

        //TODO: hata fırlatmalısın panpa
        if (relationExists(primaryId, relatedId))
            return null;

        Task existingTask = taskService.getById(primaryId);
        User existingUser = userService.getById(relatedId);

        TaskAsigneeRelation relation = TaskAsigneeRelation.builder()
                .task(existingTask)
                .user(existingUser)
                .build();

        return taskAsigneeRelationRepository.save(relation);
    }

    @Override
    public TaskAsigneeRelation getRelation(UUID primaryId, UUID relatedId) {
        //TODO: hata fırlat burda!!!
        if (primaryId == null || relatedId == null)
            return null;

        //TODO: hata fırlat
        return taskAsigneeRelationRepository
                .findByTask_IdAndUser_Id(primaryId, relatedId)
                .orElseThrow(null);
    }

    @Override
    public void removeRelation(UUID primaryId, UUID relatedId) {
        //TODO: hata fırlat burda!!!
        if (primaryId == null || relatedId == null)
            return;

        TaskAsigneeRelation relation = getRelation(primaryId, relatedId);

        taskAsigneeRelationRepository.delete(relation);
    }

    @Override
    public List<TaskAsigneeRelation> getAllRelations(UUID primaryId) {
        //TODO: hata fırlat burda!!!
        if (primaryId == null)
            return null;

        return taskAsigneeRelationRepository.findByTask_Id(primaryId);
    }

    @Override
    public List<TaskAsigneeRelation> getAllRelationsByRelatedId(UUID relatedId) {
        //TODO: hata fırlat burda!!!
        if (relatedId == null)
            return null;

        return taskAsigneeRelationRepository.findByUser_Id(relatedId);
    }

    @Override
    public void removeAllRelations(UUID primaryId) {
        //TODO: hata fırlat burda!!!
        if (primaryId == null)
            return;

        taskAsigneeRelationRepository.deleteAllByTask_Id(primaryId);
    }

    @Override
    public void removeAllRelationsByRelatedId(UUID relatedId) {
        //TODO: hata fırlat burda!!!
        if (relatedId == null)
            return;

        taskAsigneeRelationRepository.deleteAllByUser_Id(relatedId);
    }

    @Override
    public List<User> getRelatedEntities(UUID primaryId) {
        //TODO: hata fırlat burda!!!
        if (primaryId == null)
            return null;

        List<TaskAsigneeRelation> relations = getAllRelations(primaryId);

        return relations.stream()
                .map(TaskAsigneeRelation::getUser)
                .toList();
    }

    @Override
    public List<Task> getPrimaryEntities(UUID relatedId) {
        //TODO: hata fırlat burda!!!
        if (relatedId == null)
            return null;

        List<TaskAsigneeRelation> relations = getAllRelationsByRelatedId(relatedId);

        return relations.stream()
                .map(TaskAsigneeRelation::getTask)
                .toList();
    }

    @Override
    public boolean relationExists(UUID primaryId, UUID relatedId) {
        //TODO: hata fırlat burda!!!
        if (primaryId == null || relatedId == null)
            return false;

        return taskAsigneeRelationRepository.existsByTask_IdAndUser_Id(primaryId, relatedId);
    }

    @Override
    public long countRelations(UUID primaryId) {
        //TODO: hata fırlat burda!!!
        if (primaryId == null)
            return 0;

        return taskAsigneeRelationRepository.countByTask_Id(primaryId);
    }

    @Override
    public long countRelationsByRelatedId(UUID relatedId) {
        //TODO: hata fırlat burda!!!
        if (relatedId == null)
            return 0;

        return taskAsigneeRelationRepository.countByUser_Id(relatedId);
    }
}
