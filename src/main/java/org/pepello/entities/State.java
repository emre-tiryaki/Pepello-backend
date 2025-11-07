package org.pepello.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.pepello.common.baseEntities.UpdatedEntity;
import org.pepello.entities.enums.Color;

@Getter
@Setter
@Entity
@Table(name = "states")
@AttributeOverride(name = "id", column = @Column(name = "state_id", nullable = false, updatable = false))
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class State extends UpdatedEntity {

    @NotNull(message = "state name cannot be null")
    @NotBlank(message = "state name cannot be blank")
    @Column(name = "state_name", nullable = false)
    private String stateName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "icon_id", nullable = false)
    private Media icon;

    @Enumerated(EnumType.STRING)
    @Column(name = "state_color", columnDefinition = "text", nullable = false)
    private Color stateColor;

}