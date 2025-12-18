package org.pepello.service.impl;

import org.pepello.common.ICrud;
import org.pepello.common.repository.BaseRelationRepository;
import org.pepello.common.service.BaseRelationService;
import org.pepello.dto.state.StateCreateRequest;
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
public class ProjectStateRelationServiceImpl extends BaseRelationService<Project, State, ProjectStateRelation> implements IProjectStateRelationService {
    @Autowired
    private ProjectStateRelationRepository projectStateRelationRepository;
    @Autowired
    private IProjectService projectService;
    @Autowired
    private IStateService stateService;


    @Override
    protected BaseRelationRepository<ProjectStateRelation> getRepository() {
        return projectStateRelationRepository;
    }

    @Override
    protected ICrud<Project, ?, ?> getPrimaryService() {
        return projectService;
    }

    @Override
    protected ICrud<State, ?, ?> getRelatedService() {
        return stateService;
    }

    @Override
    protected ProjectStateRelation buildRelation(Project project, State state) {
        return ProjectStateRelation.builder()
                .project(project)
                .state(state)
                .build();
    }

    @Override
    protected java.util.Optional<ProjectStateRelation> findRelationOptional(UUID primaryId, UUID relatedId) {
        return projectStateRelationRepository.findByProject_IdAndState_Id(primaryId, relatedId);
    }

    @Override
    protected List<ProjectStateRelation> findByPrimaryId(UUID primaryId) {
        return projectStateRelationRepository.findByProject_Id(primaryId);
    }

    @Override
    protected List<ProjectStateRelation> findByRelatedId(UUID relatedId) {
        return projectStateRelationRepository.findByState_Id(relatedId);
    }

    @Override
    protected void deleteAllByPrimaryId(UUID primaryId) {
        projectStateRelationRepository.deleteAllByProject_Id(primaryId);
    }

    @Override
    protected void deleteAllByRelatedId(UUID relatedId) {
        projectStateRelationRepository.deleteAllByState_Id(relatedId);
    }

    @Override
    protected boolean existsRelation(UUID primaryId, UUID relatedId) {
        return projectStateRelationRepository.existsByProject_IdAndState_Id(primaryId, relatedId);
    }

    @Override
    protected long countByPrimaryId(UUID primaryId) {
        return projectStateRelationRepository.countByProject_Id(primaryId);
    }

    @Override
    protected long countByRelatedId(UUID relatedId) {
        return projectStateRelationRepository.countByState_Id(relatedId);
    }

    @Override
    protected Project extractPrimary(ProjectStateRelation projectStateRelation) {
        return projectStateRelation.getProject();
    }

    @Override
    protected State extractRelated(ProjectStateRelation projectStateRelation) {
        return projectStateRelation.getState();
    }

    public State addStateToProject(UUID projectId, StateCreateRequest request) {
        if (request == null)
            throw new IllegalArgumentException("illegal argument");
        if (!projectService.exists(projectId))
            throw new RuntimeException("Project does not exists");

        State newState = stateService.create(request);

        addRelation(projectId, newState.getId());

        return newState;
    }
}
