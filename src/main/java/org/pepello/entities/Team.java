package org.pepello.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.pepello.entities.baseClasses.UpdatedEntity;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "teams")
@AttributeOverride(name = "id", column = @Column(name = "team_id", nullable = false, updatable = false))
public class Team extends UpdatedEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "icon_id", nullable = false)
    private Media iconId;

    @Column(name = "team_name", nullable = false, length = Integer.MAX_VALUE)
    private String teamName;

    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

}