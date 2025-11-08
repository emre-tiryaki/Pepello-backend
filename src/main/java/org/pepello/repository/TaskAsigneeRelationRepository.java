package org.pepello.repository;

import org.pepello.common.repository.BaseRelationRepository;
import org.pepello.entities.TaskAsigneeRelation;
import org.pepello.entities.TeamProjectRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TaskAsigneeRelationRepository extends BaseRelationRepository<TaskAsigneeRelation> {
    //find
    Optional<TaskAsigneeRelation> findByTask_IdAndUser_Id(UUID taskId, UUID userId);
    List<TaskAsigneeRelation> findByTask_Id(UUID taskId);
    List<TaskAsigneeRelation> findByUser_Id(UUID userId);

    //exist
    boolean existsByTask_IdAndUser_Id(UUID taskId, UUID userId);

    //count
    long countByTask_Id(UUID taskId);
    long countByUser_Id(UUID userId);

    //delete
    void deleteAllByTask_Id(UUID taskId);
    void deleteAllByUser_Id(UUID userId);
}
