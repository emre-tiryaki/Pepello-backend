package org.pepello.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@NoRepositoryBean
public interface BaseRelationRepository<T> extends JpaRepository<T, UUID> {
    //find
    Optional<T> findByPrimaryAndRelated(UUID primaryId, UUID relatedId);
    List<T> findAllByPrimary(UUID primaryId);
    List<T> findAllByRelated(UUID relatedId);

    //exist
    boolean existsByPrimaryAndRelated(UUID primaryId, UUID relatedId);

    //count
    long countByPrimary(UUID primaryId);
    long countByRelated(UUID relatedId);

    //delete
    void deleteAllByPrimary(UUID primaryId);
    void deleteAllByRelated(UUID relatedId);
}
