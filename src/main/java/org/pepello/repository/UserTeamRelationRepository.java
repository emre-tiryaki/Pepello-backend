package org.pepello.repository;

import org.pepello.common.repository.BaseRelationRepository;
import org.pepello.entities.UserTeamRelation;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserTeamRelationRepository extends BaseRelationRepository<UserTeamRelation> {
    //find
    Optional<UserTeamRelation> findByUser_IdAndTeam_Id(UUID userId, UUID teamId);

    List<UserTeamRelation> findByUser_Id(UUID userId);

    List<UserTeamRelation> findByTeam_Id(UUID userId);

    //exist
    boolean existsByUser_IdAndTeam_Id(UUID userId, UUID teamId);

    //count
    long countByUser_Id(UUID userId);

    long countByTeam_Id(UUID teamId);

    //delete
    void deleteAllByUser_Id(UUID userId);

    void deleteAllByTeam_Id(UUID userId);
}

