package org.pepello.service.impl;

import org.pepello.common.ICrud;
import org.pepello.common.repository.BaseRelationRepository;
import org.pepello.common.service.BaseRelationService;
import org.pepello.entities.Role;
import org.pepello.entities.RoleUserRelation;
import org.pepello.entities.User;
import org.pepello.repository.RoleUserRelationRepository;
import org.pepello.service.IRoleService;
import org.pepello.service.IRoleUserServiceService;
import org.pepello.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RoleUserRelationServiceImpl extends BaseRelationService<Role, User, RoleUserRelation> implements IRoleUserServiceService {
    @Autowired
    private RoleUserRelationRepository roleUserRelationRepository;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IUserService userService;


    @Override
    protected BaseRelationRepository<RoleUserRelation> getRepository() {
        return roleUserRelationRepository;
    }

    @Override
    protected ICrud<Role, ?, ?> getPrimaryService() {
        return roleService;
    }

    @Override
    protected ICrud<User, ?, ?> getRelatedService() {
        return userService;
    }

    @Override
    protected RoleUserRelation buildRelation(Role role, User user) {
        return RoleUserRelation.builder()
                .role(role)
                .user(user)
                .build();
    }

    @Override
    protected java.util.Optional<RoleUserRelation> findRelationOptional(UUID primaryId, UUID relatedId) {
        return roleUserRelationRepository.findByRole_IdAndUser_Id(primaryId, relatedId);
    }

    @Override
    protected List<RoleUserRelation> findByPrimaryId(UUID primaryId) {
        return roleUserRelationRepository.findByRole_Id(primaryId);
    }

    @Override
    protected List<RoleUserRelation> findByRelatedId(UUID relatedId) {
        return roleUserRelationRepository.findByUser_Id(relatedId);
    }

    @Override
    protected void deleteAllByPrimaryId(UUID primaryId) {
        roleUserRelationRepository.deleteAllByRole_Id(primaryId);
    }

    @Override
    protected void deleteAllByRelatedId(UUID relatedId) {
        roleUserRelationRepository.deleteAllByUser_Id(relatedId);
    }

    @Override
    protected boolean existsRelation(UUID primaryId, UUID relatedId) {
        return roleUserRelationRepository.existsByRole_IdAndUser_Id(primaryId, relatedId);
    }

    @Override
    protected long countByPrimaryId(UUID primaryId) {
        return roleUserRelationRepository.countByRole_Id(primaryId);
    }

    @Override
    protected long countByRelatedId(UUID relatedId) {
        return roleUserRelationRepository.countByUser_Id(relatedId);
    }

    @Override
    protected Role extractPrimary(RoleUserRelation roleUserRelation) {
        return roleUserRelation.getRole();
    }

    @Override
    protected User extractRelated(RoleUserRelation roleUserRelation) {
        return roleUserRelation.getUser();
    }
}
