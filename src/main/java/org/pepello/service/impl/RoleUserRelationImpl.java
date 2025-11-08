package org.pepello.service.impl;

import org.pepello.entities.Role;
import org.pepello.entities.RoleUserRelation;
import org.pepello.entities.User;
import org.pepello.repository.RoleUserRelationRepository;
import org.pepello.service.IRoleService;
import org.pepello.service.IRoleUserService;
import org.pepello.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RoleUserRelationImpl implements IRoleUserService {
    @Autowired
    private RoleUserRelationRepository roleUserRelationRepository;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IUserService userService;

    @Override
    public RoleUserRelation addRelation(UUID primaryId, UUID relatedId) {
        //TODO: hata fırlat burda!!!
        if (primaryId == null || relatedId == null)
            return null;

        //TODO: hata fırlatmalısın panpa
        if (relationExists(primaryId, relatedId))
            return null;

        Role existingRole = roleService.getById(primaryId);
        User existingUser = userService.getById(relatedId);

        RoleUserRelation relation = RoleUserRelation.builder()
                .role(existingRole)
                .user(existingUser)
                .build();

        return roleUserRelationRepository.save(relation);
    }

    @Override
    public RoleUserRelation getRelation(UUID primaryId, UUID relatedId) {
        //TODO: hata fırlat burda!!!
        if (primaryId == null || relatedId == null)
            return null;

        //TODO: hata!!
        return roleUserRelationRepository
                .findByRole_IdAndUser_Id(primaryId, relatedId)
                .orElseThrow(null);
    }

    @Override
    public void removeRelation(UUID primaryId, UUID relatedId) {
        //TODO: hata fırlat burda!!!
        if (primaryId == null || relatedId == null)
            return;

        RoleUserRelation relation = getRelation(primaryId, relatedId);

        roleUserRelationRepository.delete(relation);
    }

    @Override
    public List<RoleUserRelation> getAllRelations(UUID primaryId) {
        //TODO: hata fırlat burda!!!
        if (primaryId == null)
            return null;

        return roleUserRelationRepository.findByRole_Id(primaryId);
    }

    @Override
    public List<RoleUserRelation> getAllRelationsByRelatedId(UUID relatedId) {
        //TODO: hata fırlat burda!!!
        if (relatedId == null)
            return null;

        return roleUserRelationRepository.findByUser_Id(relatedId);
    }

    @Override
    public void removeAllRelations(UUID primaryId) {
        //TODO: hata fırlat burda!!!
        if (primaryId == null)
            return;

        roleUserRelationRepository.deleteAllByRole_Id(primaryId);
    }

    @Override
    public void removeAllRelationsByRelatedId(UUID relatedId) {
        //TODO: hata fırlat burda!!!
        if (relatedId == null)
            return;

        roleUserRelationRepository.deleteAllByUser_Id(relatedId);
    }

    @Override
    public List<User> getRelatedEntities(UUID primaryId) {
        //TODO: hata fırlat burda!!!
        if (primaryId == null)
            return null;

        List<RoleUserRelation> relations = getAllRelations(primaryId);

        return relations.stream()
                .map(RoleUserRelation::getUser)
                .toList();
    }

    @Override
    public List<Role> getPrimaryEntities(UUID relatedId) {
        //TODO: hata fırlat burda!!!
        if (relatedId == null)
            return null;

        List<RoleUserRelation> relations = getAllRelationsByRelatedId(relatedId);

        return relations.stream()
                .map(RoleUserRelation::getRole)
                .toList();
    }

    @Override
    public boolean relationExists(UUID primaryId, UUID relatedId) {
        //TODO: hata fırlat burda!!!
        if (primaryId == null || relatedId == null)
            return false;

        return roleUserRelationRepository.existsByRole_IdAndUser_Id(primaryId, relatedId);
    }

    @Override
    public long countRelations(UUID primaryId) {
        //TODO: hata fırlat burda!!!
        if (primaryId == null)
            return 0;

        return roleUserRelationRepository.countByRole_Id(primaryId);
    }

    @Override
    public long countRelationsByRelatedId(UUID relatedId) {
        //TODO: hata fırlat burda!!!
        if (relatedId == null)
            return 0;

        return roleUserRelationRepository.countByUser_Id(relatedId);
    }
}
