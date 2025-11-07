package org.pepello.entities;

import jakarta.persistence.*;
import lombok.*;
import org.pepello.common.baseEntities.CreatedEntity;

@Getter
@Setter
@Entity
@Table(name = "project_state_relations")
@AttributeOverride(name = "id", column = @Column(name = "relation_id", nullable = false, updatable = false))
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectStateRelation extends CreatedEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_id", nullable = false)
    private State state;

}