package org.pepello.service.impl;

import jakarta.transaction.Transactional;
import org.pepello.common.ICrud;
import org.pepello.common.repository.BaseRelationRepository;
import org.pepello.common.service.BaseRelationService;
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
public class UserTeamRelationServiceImpl extends BaseRelationService<User, Team, UserTeamRelation> implements IUserTeamRelationService {
    @Autowired
    private UserTeamRelationRepository userTeamRelationRepository;
    @Autowired
    private IUserService userService;
    @Autowired
    private ITeamService teamService;


    @Override
    protected BaseRelationRepository<UserTeamRelation> getRepository() {
        return userTeamRelationRepository;
    }

    @Override
    protected ICrud<User, ?, ?> getPrimaryService() {
        return userService;
    }

    @Override
    protected ICrud<Team, ?, ?> getRelatedService() {
        return teamService;
    }

    @Override
    protected UserTeamRelation buildRelation(User user, Team team) {
        return UserTeamRelation.builder()
                .user(user)
                .team(team)
                .build();
    }

    @Override
    protected java.util.Optional<UserTeamRelation> findRelationOptional(UUID primaryId, UUID relatedId) {
        return userTeamRelationRepository.findByUser_IdAndTeam_Id(primaryId, relatedId);
    }

    @Override
    protected List<UserTeamRelation> findByPrimaryId(UUID primaryId) {
        return userTeamRelationRepository.findByUser_Id(primaryId);
    }

    @Override
    protected List<UserTeamRelation> findByRelatedId(UUID relatedId) {
        return userTeamRelationRepository.findByTeam_Id(relatedId);
    }

    @Override
    protected void deleteAllByPrimaryId(UUID primaryId) {
        userTeamRelationRepository.deleteAllByUser_Id(primaryId);
    }

    @Override
    protected void deleteAllByRelatedId(UUID relatedId) {
        userTeamRelationRepository.deleteAllByTeam_Id(relatedId);
    }

    @Override
    protected boolean existsRelation(UUID primaryId, UUID relatedId) {
        return userTeamRelationRepository.existsByUser_IdAndTeam_Id(primaryId, relatedId);
    }

    @Override
    protected long countByPrimaryId(UUID primaryId) {
        return userTeamRelationRepository.countByUser_Id(primaryId);
    }

    @Override
    protected long countByRelatedId(UUID relatedId) {
        return userTeamRelationRepository.countByTeam_Id(relatedId);
    }

    @Override
    protected User extractPrimary(UserTeamRelation userTeamRelation) {
        return userTeamRelation.getUser();
    }

    @Override
    protected Team extractRelated(UserTeamRelation userTeamRelation) {
        return userTeamRelation.getTeam();
    }
}
