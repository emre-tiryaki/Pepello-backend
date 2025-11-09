package org.pepello.service.impl;

import jakarta.transaction.Transactional;
import org.pepello.common.ICrud;
import org.pepello.common.repository.BaseRelationRepository;
import org.pepello.common.service.BaseRelationService;
import org.pepello.entities.Project;
import org.pepello.entities.Team;
import org.pepello.entities.TeamProjectRelation;
import org.pepello.repository.TeamProjectRelationRepository;
import org.pepello.service.IProjectService;
import org.pepello.service.ITeamProjectRelationService;
import org.pepello.service.ITeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class TeamProjectRelationServiceImpl extends BaseRelationService<Team, Project, TeamProjectRelation> implements ITeamProjectRelationService {
    @Autowired
    private TeamProjectRelationRepository teamProjectRelationRepository;
    @Autowired
    private ITeamService teamService;
    @Autowired
    private IProjectService projectService;


    @Override
    protected BaseRelationRepository<TeamProjectRelation> getRepository() {
        return teamProjectRelationRepository;
    }

    @Override
    protected ICrud<Team, ?, ?> getPrimaryService() {
        return teamService;
    }

    @Override
    protected ICrud<Project, ?, ?> getRelatedService() {
        return projectService;
    }

    @Override
    protected TeamProjectRelation buildRelation(Team team, Project project) {
        return TeamProjectRelation.builder()
                .team(team)
                .project(project)
                .build();
    }

    @Override
    protected java.util.Optional<TeamProjectRelation> findRelationOptional(UUID primaryId, UUID relatedId) {
        return teamProjectRelationRepository.findByTeam_IdAndProject_Id(primaryId, relatedId);
    }

    @Override
    protected List<TeamProjectRelation> findByPrimaryId(UUID primaryId) {
        return teamProjectRelationRepository.findByTeam_Id(primaryId);
    }

    @Override
    protected List<TeamProjectRelation> findByRelatedId(UUID relatedId) {
        return teamProjectRelationRepository.findByProject_Id(relatedId);
    }

    @Override
    protected void deleteAllByPrimaryId(UUID primaryId) {
        teamProjectRelationRepository.deleteAllByTeam_Id(primaryId);
    }

    @Override
    protected void deleteAllByRelatedId(UUID relatedId) {
        teamProjectRelationRepository.deleteAllByProject_Id(relatedId);
    }

    @Override
    protected boolean existsRelation(UUID primaryId, UUID relatedId) {
        return teamProjectRelationRepository.existsByTeam_IdAndProject_Id(primaryId, relatedId);
    }

    @Override
    protected long countByPrimaryId(UUID primaryId) {
        return teamProjectRelationRepository.countByTeam_Id(primaryId);
    }

    @Override
    protected long countByRelatedId(UUID relatedId) {
        return teamProjectRelationRepository.countByProject_Id(relatedId);
    }

    @Override
    protected Team extractPrimary(TeamProjectRelation teamProjectRelation) {
        return teamProjectRelation.getTeam();
    }

    @Override
    protected Project extractRelated(TeamProjectRelation teamProjectRelation) {
        return teamProjectRelation.getProject();
    }
}
