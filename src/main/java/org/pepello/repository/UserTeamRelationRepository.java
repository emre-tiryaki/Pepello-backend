package org.pepello.repository;

import org.pepello.common.repository.BaseRelationRepository;
import org.pepello.entities.UserTeamRelation;
import org.springframework.data.jpa.repository.JpaRepository;
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

    @Override
    default Optional<UserTeamRelation> findByPrimaryAndRelated(UUID primaryId, UUID relatedId) {
        return findByUser_IdAndTeam_Id(primaryId, relatedId);
    }

    @Override
    default List<UserTeamRelation> findAllByPrimary(UUID primaryId) {
        return findByUser_Id(primaryId);
    }

    @Override
    default List<UserTeamRelation> findAllByRelated(UUID relatedId) {
        return findByTeam_Id(relatedId);
    }

    @Override
    default boolean existsByPrimaryAndRelated(UUID primaryId, UUID relatedId) {
        return existsByUser_IdAndTeam_Id(primaryId, relatedId);
    }

    @Override
    default long countByPrimary(UUID primaryId) {
        return countByUser_Id(primaryId);
    }

    @Override
    default long countByRelated(UUID relatedId) {
        return countByTeam_Id(relatedId);
    }

    @Override
    default void deleteAllByPrimary(UUID primaryId) {
        deleteAllByUser_Id(primaryId);
    }

    @Override
    default void deleteAllByRelated(UUID relatedId) {
        deleteAllByTeam_Id(relatedId);
    }
}

