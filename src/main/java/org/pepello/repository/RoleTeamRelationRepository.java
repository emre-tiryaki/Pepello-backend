package org.pepello.repository;

import org.pepello.common.repository.BaseRelationRepository;
import org.pepello.entities.RoleTeamRelation;
import org.pepello.entities.RoleUserRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleTeamRelationRepository extends BaseRelationRepository<RoleTeamRelation> {
    //find
    Optional<RoleTeamRelation> findByRole_IdAndUser_Id(UUID roleId, UUID teamId);
    List<RoleTeamRelation> findByRole_Id(UUID roleId);
    List<RoleTeamRelation> findByTeam_Id(UUID teamId);

    //exist
    boolean existsByRole_IdAndTeam_Id(UUID roleId, UUID teamId);

    //count
    long countByRole_Id(UUID roleId);
    long countByTeam_Id(UUID teamId);

    //delete
    void deleteAllByRole_Id(UUID roleId);
    void deleteAllByTeam_Id(UUID teamId);
}
