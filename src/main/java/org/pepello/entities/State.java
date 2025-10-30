package org.pepello.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.pepello.entities.baseClasses.UpdatedEntity;
import org.pepello.entities.enums.Color;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "states")
@AttributeOverride(name = "id", column = @Column(name = "state_id", nullable = false, updatable = false))
public class State extends UpdatedEntity {

    @Column(name = "state_name", nullable = false, length = Integer.MAX_VALUE)
    private String stateName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "icon_id", nullable = false)
    private Media icon;

    @Enumerated(EnumType.STRING)
    @Column(name = "state_color", columnDefinition = "text", nullable = false)
    private Color stateColor;

}