package org.pepello.service.impl;

import jakarta.transaction.Transactional;
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
public class TeamProjectRelationServiceImpl implements ITeamProjectRelationService {
    @Autowired
    private TeamProjectRelationRepository teamProjectRelationRepository;
    @Autowired
    private ITeamService teamService;
    @Autowired
    private IProjectService projectService;

    @Override
    public TeamProjectRelation addRelation(UUID primaryId, UUID relatedId) {
        //TODO: hata fırlat burda!!!
        if (primaryId == null || relatedId == null)
            return null;

        //TODO: hata fırlatmalısın panpa
        if (relationExists(primaryId, relatedId))
            return null;

        Team existingTeam = teamService.getById(primaryId);
        Project existingProject = projectService.getById(relatedId);

        TeamProjectRelation relation = TeamProjectRelation.builder()
                .team(existingTeam)
                .project(existingProject)
                .build();

        return teamProjectRelationRepository.save(relation);
    }

    @Override
    public TeamProjectRelation getRelation(UUID primaryId, UUID relatedId) {
        //TODO: hata fırlat burda!!!
        if (primaryId == null || relatedId == null)
            return null;

        //TODO: HAATAAAAAAA FIRLAT AW
        if (!teamService.exists(primaryId) || projectService.exists(relatedId))
            return null;

        //TODO: hata fırlatmalısın
        return teamProjectRelationRepository
                .findByTeam_IdAndProject_Id(primaryId, relatedId)
                .orElseThrow(null);
    }

    @Override
    public void removeRelation(UUID primaryId, UUID relatedId) {
        //TODO: hata fırlat burda!!!
        if (primaryId == null || relatedId == null)
            return;

        TeamProjectRelation relation = getRelation(primaryId, relatedId);

        teamProjectRelationRepository.delete(relation);
    }

    @Override
    public List<TeamProjectRelation> getAllRelations(UUID primaryId) {
        //TODO: hata fırlat burda!!!
        if (primaryId == null)
            return null;

        return teamProjectRelationRepository.findByTeam_Id(primaryId);
    }

    @Override
    public List<TeamProjectRelation> getAllRelationsByRelatedId(UUID relatedId) {
        //TODO: hata fırlat burda!!!
        if (relatedId == null)
            return null;

        return teamProjectRelationRepository.findByProject_Id(relatedId);
    }

    @Override
    public void removeAllRelations(UUID primaryId) {
        //TODO: hata fırlatmalısın
        if (primaryId == null)
            return;

        teamProjectRelationRepository.deleteAllByTeam_Id(primaryId);
    }

    @Override
    public void removeAllRelationsByRelatedId(UUID relatedId) {
        //TODO: hata fırlatmalısın
        if (relatedId == null)
            return;

        teamProjectRelationRepository.deleteAllByProject_Id(relatedId);
    }

    @Override
    public List<Project> getRelatedEntities(UUID primaryId) {
        //TODO: hata fırlatmalısın
        if (primaryId == null)
            return null;

        List<TeamProjectRelation> relations = getAllRelations(primaryId);

        return relations.stream()
                .map(TeamProjectRelation::getProject)
                .toList();
    }

    @Override
    public List<Team> getPrimaryEntities(UUID relatedId) {
        //TODO: hata fırlatmalısın
        if (relatedId == null)
            return null;

        List<TeamProjectRelation> relations = getAllRelationsByRelatedId(relatedId);

        return relations.stream()
                .map(TeamProjectRelation::getTeam)
                .toList();
    }

    @Override
    public boolean relationExists(UUID primaryId, UUID relatedId) {
        //TODO: hata fırlat burda!!!
        if (primaryId == null || relatedId == null)
            return false;

        return teamProjectRelationRepository.existsByTeam_IdAndProject_Id(primaryId, relatedId);
    }

    @Override
    public long countRelations(UUID primaryId) {
        //TODO: hata fırlat burda!!!
        if (primaryId == null)
            return 0;

        return teamProjectRelationRepository.countByTeam_Id(primaryId);
    }

    @Override
    public long countRelationsByRelatedId(UUID relatedId) {
        //TODO: hata fırlat burda!!!
        if (relatedId == null)
            return 0;

        return teamProjectRelationRepository.countByProject_Id(relatedId);
    }
}
