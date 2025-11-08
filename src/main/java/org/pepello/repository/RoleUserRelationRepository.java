package org.pepello.repository;

import org.pepello.common.repository.BaseRelationRepository;
import org.pepello.entities.RoleUserRelation;
import org.pepello.entities.TaskAsigneeRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleUserRelationRepository extends BaseRelationRepository<RoleUserRelation> {
    //find
    Optional<RoleUserRelation> findByRole_IdAndUser_Id(UUID roleId, UUID userId);
    List<RoleUserRelation> findByRole_Id(UUID roleId);
    List<RoleUserRelation> findByUser_Id(UUID userId);

    //exist
    boolean existsByRole_IdAndUser_Id(UUID roleId, UUID userId);

    //count
    long countByRole_Id(UUID roleId);
    long countByUser_Id(UUID userId);

    //delete
    void deleteAllByRole_Id(UUID roleId);
    void deleteAllByUser_Id(UUID userId);


}
