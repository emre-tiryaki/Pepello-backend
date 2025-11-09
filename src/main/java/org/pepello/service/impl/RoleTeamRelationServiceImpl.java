package org.pepello.service.impl;

import org.pepello.common.ICrud;
import org.pepello.common.repository.BaseRelationRepository;
import org.pepello.common.service.BaseRelationService;
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
public class RoleTeamRelationServiceImpl extends BaseRelationService<Role, Team, RoleTeamRelation> implements IRoleTeamRelationService {
    @Autowired
    private RoleTeamRelationRepository roleTeamRelationRepository;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private ITeamService teamService;

    @Override
    protected BaseRelationRepository<RoleTeamRelation> getRepository() {
        return roleTeamRelationRepository;
    }

    @Override
    protected ICrud<Role, ?, ?> getPrimaryService() {
        return roleService;
    }

    @Override
    protected ICrud<Team, ?, ?> getRelatedService() {
        return teamService;
    }

    @Override
    protected RoleTeamRelation buildRelation(Role role, Team team) {
        return RoleTeamRelation.builder()
                .role(role)
                .team(team)
                .build();
    }

    @Override
    protected java.util.Optional<RoleTeamRelation> findRelationOptional(UUID primaryId, UUID relatedId) {
        return roleTeamRelationRepository.findByRole_IdAndTeam_Id(primaryId, relatedId);
    }

    @Override
    protected List<RoleTeamRelation> findByPrimaryId(UUID primaryId) {
        return roleTeamRelationRepository.findByRole_Id(primaryId);
    }

    @Override
    protected List<RoleTeamRelation> findByRelatedId(UUID relatedId) {
        return roleTeamRelationRepository.findByTeam_Id(relatedId);
    }

    @Override
    protected void deleteAllByPrimaryId(UUID primaryId) {
        roleTeamRelationRepository.deleteAllByRole_Id(primaryId);
    }

    @Override
    protected void deleteAllByRelatedId(UUID relatedId) {
        roleTeamRelationRepository.deleteAllByTeam_Id(relatedId);
    }

    @Override
    protected boolean existsRelation(UUID primaryId, UUID relatedId) {
        return roleTeamRelationRepository.existsByRole_IdAndTeam_Id(primaryId, relatedId);
    }

    @Override
    protected long countByPrimaryId(UUID primaryId) {
        return roleTeamRelationRepository.countByRole_Id(primaryId);
    }

    @Override
    protected long countByRelatedId(UUID relatedId) {
        return roleTeamRelationRepository.countByTeam_Id(relatedId);
    }

    @Override
    protected Role extractPrimary(RoleTeamRelation roleTeamRelation) {
        return roleTeamRelation.getRole();
    }

    @Override
    protected Team extractRelated(RoleTeamRelation roleTeamRelation) {
        return roleTeamRelation.getTeam();
    }
}
