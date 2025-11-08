package org.pepello.repository;

import org.pepello.common.repository.BaseRelationRepository;
import org.pepello.entities.ProjectStateRelation;
import org.pepello.entities.RoleTeamRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProjectStateRelationRepository extends BaseRelationRepository<ProjectStateRelation> {
    //find
    Optional<ProjectStateRelation> findByProject_IdAndState_Id(UUID projectId, UUID stateId);
    List<ProjectStateRelation> findByProject_Id(UUID projectId);
    List<ProjectStateRelation> findByState_Id(UUID stateId);

    //exist
    boolean existsByProject_IdAndState_Id(UUID projectId, UUID stateId);

    //count
    long countByProject_Id(UUID projectId);
    long countByState_Id(UUID stateId);

    //delete
    void deleteAllByProject_Id(UUID projectId);
    void deleteAllByState_Id(UUID stateId);
}
