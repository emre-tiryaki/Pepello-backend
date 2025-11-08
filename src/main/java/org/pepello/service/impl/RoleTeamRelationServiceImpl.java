package org.pepello.service.impl;

import org.pepello.entities.Role;
import org.pepello.entities.RoleTeamRelation;
import org.pepello.entities.Team;
import org.pepello.repository.RoleTeamRelationRepository;
import org.pepello.service.IRoleService;
import org.pepello.service.IRoleTeamRelationService;
import org.pepello.service.ITeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RoleTeamRelationServiceImpl implements IRoleTeamRelationService {
    @Autowired
    private RoleTeamRelationRepository roleTeamRelationRepository;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private ITeamService teamService;

    @Override
    public RoleTeamRelation addRelation(UUID primaryId, UUID relatedId) {
        //TODO: hata fırlat burda!!!
        if (primaryId == null || relatedId == null)
            return null;

        //TODO: hata fırlatmalısın panpa
        if (relationExists(primaryId, relatedId))
            return null;

        Role existingRole = roleService.getById(primaryId);
        Team existingTeam = teamService.getById(relatedId);

        RoleTeamRelation relation = RoleTeamRelation.builder()
                .role(existingRole)
                .team(existingTeam)
                .build();

        return roleTeamRelationRepository.save(relation);
    }

    @Override
    public RoleTeamRelation getRelation(UUID primaryId, UUID relatedId) {
        //TODO: hata fırlat burda!!!
        if (primaryId == null || relatedId == null)
            return null;

        //TODO: hata fırlat
        return roleTeamRelationRepository
                .findByRole_IdAndUser_Id(primaryId, relatedId)
                .orElseThrow(null);
    }

    @Override
    public void removeRelation(UUID primaryId, UUID relatedId) {
        //TODO: hata fırlat burda!!!
        if (primaryId == null || relatedId == null)
            return;

        RoleTeamRelation relation = getRelation(primaryId, relatedId);

        roleTeamRelationRepository.delete(relation);
    }

    @Override
    public List<RoleTeamRelation> getAllRelations(UUID primaryId) {
        //TODO: hata fırlat burda!!!
        if (primaryId == null)
            return null;

        return roleTeamRelationRepository.findByRole_Id(primaryId);
    }

    @Override
    public List<RoleTeamRelation> getAllRelationsByRelatedId(UUID relatedId) {
        //TODO: hata fırlat burda!!!
        if (relatedId == null)
            return null;

        return roleTeamRelationRepository.findByTeam_Id(relatedId);
    }

    @Override
    public void removeAllRelations(UUID primaryId) {
        //TODO: hata fırlat burda!!!
        if (primaryId == null)
            return;

        roleTeamRelationRepository.deleteAllByRole_Id(primaryId);
    }

    @Override
    public void removeAllRelationsByRelatedId(UUID relatedId) {
        //TODO: hata fırlat burda!!!
        if (relatedId == null)
            return;

        roleTeamRelationRepository.deleteAllByTeam_Id(relatedId);
    }

    @Override
    public List<Team> getRelatedEntities(UUID primaryId) {
        //TODO: hata fırlat burda!!!
        if (primaryId == null)
            return null;

        List<RoleTeamRelation> relations = getAllRelations(primaryId);

        return relations.stream()
                .map(RoleTeamRelation::getTeam)
                .toList();
    }

    @Override
    public List<Role> getPrimaryEntities(UUID relatedId) {
        //TODO: hata fırlat burda!!!
        if (relatedId == null)
            return null;
        List<RoleTeamRelation> relations = getAllRelationsByRelatedId(relatedId);

        return relations.stream()
                .map(RoleTeamRelation::getRole)
                .toList();
    }

    @Override
    public boolean relationExists(UUID primaryId, UUID relatedId) {
        //TODO: hata fırlat burda!!!
        if (primaryId == null || relatedId == null)
            return false;

        return roleTeamRelationRepository.existsByRole_IdAndTeam_Id(primaryId, relatedId);
    }

    @Override
    public long countRelations(UUID primaryId) {
        //TODO: hata fırlat burda!!!
        if (primaryId == null)
            return 0;

        return roleTeamRelationRepository.countByRole_Id(primaryId);
    }

    @Override
    public long countRelationsByRelatedId(UUID relatedId) {
        //TODO: hata fırlat burda!!!
        if (relatedId == null)
            return 0;

        return roleTeamRelationRepository.countByTeam_Id(relatedId);
    }
}
