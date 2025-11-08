package org.pepello.service.impl;

import jakarta.transaction.Transactional;
import org.pepello.entities.Team;
import org.pepello.entities.User;
import org.pepello.entities.UserTeamRelation;
import org.pepello.repository.UserTeamRelationRepository;
import org.pepello.service.ITeamService;
import org.pepello.service.IUserService;
import org.pepello.service.IUserTeamRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class UserTeamRelationServiceImpl implements IUserTeamRelationService {
    @Autowired
    private UserTeamRelationRepository userTeamRelationRepository;
    @Autowired
    private IUserService userService;
    @Autowired
    private ITeamService teamService;

    @Override
    public UserTeamRelation addRelation(UUID primaryId, UUID relatedId) {
        //TODO: hata fırlat burda!!!
        if (primaryId == null || relatedId == null)
            return null;

        //TODO: hata fırlatmalısın panpa
        if (relationExists(primaryId, relatedId))
            return null;

        User existingUser = userService.getById(primaryId);
        Team existingTeam = teamService.getById(relatedId);

        UserTeamRelation relation = UserTeamRelation.builder()
                .user(existingUser)
                .team(existingTeam)
                .build();

        return userTeamRelationRepository.save(relation);
    }

    @Override
    public UserTeamRelation getRelation(UUID primaryId, UUID relatedId) {
        //TODO: hata fırlat burda!!!
        if (primaryId == null || relatedId == null)
            return null;

        //TODO: hata at!!!
        if (!userService.exists(primaryId) || !teamService.exists(relatedId))
            return null;

        //TODO: hata fırlatsana
        return userTeamRelationRepository
                .findByUser_IdAndTeam_Id(primaryId, relatedId)
                .orElseThrow(null);
    }

    @Override
    public void removeRelation(UUID primaryId, UUID relatedId) {
        //TODO: hata fırlat burda!!!
        if (primaryId == null || relatedId == null)
            return;

        UserTeamRelation relation = getRelation(primaryId, relatedId);

        userTeamRelationRepository.delete(relation);
    }

    @Override
    public List<UserTeamRelation> getAllRelations(UUID primaryId) {
        //TODO: hata fırlat burda!!!
        if (primaryId == null)
            return null;

        return userTeamRelationRepository.findByUser_Id(primaryId);
    }

    @Override
    public List<UserTeamRelation> getAllRelationsByRelatedId(UUID relatedId) {
        //TODO: hata fırlat burda!!!
        if (relatedId == null)
            return null;

        return userTeamRelationRepository.findByTeam_Id(relatedId);
    }

    @Override
    public void removeAllRelations(UUID primaryId) {
        //TODO: hata fırlat burda!!!
        if (primaryId == null)
            return;

        userTeamRelationRepository.deleteAllByUser_Id(primaryId);
    }

    @Override
    public void removeAllRelationsByRelatedId(UUID relatedId) {
        //TODO: hata fırlat burda!!!
        if (relatedId == null)
            return;

        userTeamRelationRepository.deleteAllByTeam_Id(relatedId);
    }

    @Override
    public List<Team> getRelatedEntities(UUID primaryId) {
        //TODO: hata fırlat burda!!!
        if (primaryId == null)
            return null;

        List<UserTeamRelation> relations = getAllRelations(primaryId);

        return relations.stream()
                .map(UserTeamRelation::getTeam)
                .toList();
    }

    @Override
    public List<User> getPrimaryEntities(UUID relatedId) {
        //TODO: hata fırlat burda!!!
        if (relatedId == null)
            return null;

        List<UserTeamRelation> relations = getAllRelationsByRelatedId(relatedId);

        return relations.stream()
                .map(UserTeamRelation::getUser)
                .toList();
    }

    @Override
    public boolean relationExists(UUID primaryId, UUID relatedId) {
        //TODO: hata fırlat burda!!!
        if (primaryId == null || relatedId == null)
            return false;

        return userTeamRelationRepository.existsByUser_IdAndTeam_Id(primaryId, relatedId);
    }

    @Override
    public long countRelations(UUID primaryId) {
        //TODO: hata fırlat burda!!!
        if (primaryId == null)
            return 0;

        return userTeamRelationRepository.countByUser_Id(primaryId);
    }

    @Override
    public long countRelationsByRelatedId(UUID relatedId) {
        //TODO: hata fırlat burda!!!
        if (relatedId == null)
            return 0;

        return userTeamRelationRepository.countByTeam_Id(relatedId);
    }
}
