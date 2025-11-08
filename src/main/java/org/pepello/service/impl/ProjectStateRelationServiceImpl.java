package org.pepello.service.impl;

import org.pepello.entities.Project;
import org.pepello.entities.ProjectStateRelation;
import org.pepello.entities.State;
import org.pepello.repository.ProjectStateRelationRepository;
import org.pepello.service.IProjectService;
import org.pepello.service.IProjectStateRelationService;
import org.pepello.service.IStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProjectStateRelationServiceImpl implements IProjectStateRelationService {
    @Autowired
    private ProjectStateRelationRepository projectStateRelationRepository;
    @Autowired
    private IProjectService projectService;
    @Autowired
    private IStateService stateService;

    @Override
    public ProjectStateRelation addRelation(UUID primaryId, UUID relatedId) {
        //TODO: hata fırlat burda!!!
        if (primaryId == null || relatedId == null)
            return null;

        Project existingProject = projectService.getById(primaryId);
        State existingState = stateService.getById(relatedId);

        ProjectStateRelation relation = ProjectStateRelation.builder()
                .project(existingProject)
                .state(existingState)
                .build();

        return projectStateRelationRepository.save(relation);
    }

    @Override
    public ProjectStateRelation getRelation(UUID primaryId, UUID relatedId) {
        //TODO: hata fırlat burda!!!
        if (primaryId == null || relatedId == null)
            return null;

        //TODO: hata fırlat koç
        return projectStateRelationRepository
                .findByProject_IdAndState_Id(primaryId, relatedId)
                .orElseThrow(null);
    }

    @Override
    public void removeRelation(UUID primaryId, UUID relatedId) {
        //TODO: hata fırlat burda!!!
        if (primaryId == null || relatedId == null)
            return;

        ProjectStateRelation relation = getRelation(primaryId, relatedId);

        projectStateRelationRepository.delete(relation);
    }

    @Override
    public List<ProjectStateRelation> getAllRelations(UUID primaryId) {
        //TODO: hata fırlat burda!!!
        if (primaryId == null)
            return null;

        return projectStateRelationRepository.findByProject_Id(primaryId);
    }

    @Override
    public List<ProjectStateRelation> getAllRelationsByRelatedId(UUID relatedId) {
        //TODO: hata fırlat burda!!!
        if (relatedId == null)
            return null;

        return projectStateRelationRepository.findByState_Id(relatedId);
    }

    @Override
    public void removeAllRelations(UUID primaryId) {
        //TODO: hata fırlat burda!!!
        if (primaryId == null)
            return;

        projectStateRelationRepository.deleteAllByProject_Id(primaryId);
    }

    @Override
    public void removeAllRelationsByRelatedId(UUID relatedId) {
        //TODO: hata fırlat burda!!!
        if (relatedId == null)
            return;

        projectStateRelationRepository.deleteAllByState_Id(relatedId);
    }

    @Override
    public List<State> getRelatedEntities(UUID primaryId) {
        //TODO: hata fırlat burda!!!
        if (primaryId == null)
            return null;

        List<ProjectStateRelation> relations = getAllRelations(primaryId);

        return relations.stream()
                .map(ProjectStateRelation::getState)
                .toList();
    }

    @Override
    public List<Project> getPrimaryEntities(UUID relatedId) {
        //TODO: hata fırlat burda!!!
        if (relatedId == null)
            return null;

        List<ProjectStateRelation> relations = getAllRelationsByRelatedId(relatedId);

        return relations.stream()
                .map(ProjectStateRelation::getProject)
                .toList();
    }

    @Override
    public boolean relationExists(UUID primaryId, UUID relatedId) {
        //TODO: hata fırlat burda!!!
        if (primaryId == null || relatedId == null)
            return false;

        return projectStateRelationRepository.existsByProject_IdAndState_Id(primaryId, relatedId);
    }

    @Override
    public long countRelations(UUID primaryId) {
        //TODO: hata fırlat burda!!!
        if (primaryId == null)
            return 0;

        return projectStateRelationRepository.countByProject_Id(primaryId);
    }

    @Override
    public long countRelationsByRelatedId(UUID relatedId) {
        //TODO: hata fırlat burda!!!
        if (relatedId == null)
            return 0;

        return projectStateRelationRepository.countByState_Id(relatedId);
    }
}
