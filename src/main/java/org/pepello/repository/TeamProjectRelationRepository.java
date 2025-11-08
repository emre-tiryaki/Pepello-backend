package org.pepello.repository;

import org.pepello.common.repository.BaseRelationRepository;
import org.pepello.entities.TeamProjectRelation;
import org.pepello.entities.UserTeamRelation;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TeamProjectRelationRepository extends BaseRelationRepository<TeamProjectRelation> {
    //find
    Optional<TeamProjectRelation> findByTeam_IdAndProject_Id(UUID teamId, UUID projectId);
    List<TeamProjectRelation> findByTeam_Id(UUID teamId);
    List<TeamProjectRelation> findByProject_Id(UUID projectId);

    //exist
    boolean existsByTeam_IdAndProject_Id(UUID teamId, UUID projectId);

    //count
    long countByTeam_Id(UUID teamId);
    long countByProject_Id(UUID projectId);

    //delete
    void deleteAllByTeam_Id(UUID teamId);
    void deleteAllByProject_Id(UUID projectId);


}
