package org.pepello.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.pepello.common.baseEntities.UpdatedEntity;
import org.pepello.common.entity.Prototype;
import org.pepello.entities.enums.Color;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "states")
@AttributeOverride(name = "id", column = @Column(name = "state_id", nullable = false, updatable = false))
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class State extends UpdatedEntity implements Prototype<State> {

    @NotNull(message = "state name cannot be null")
    @NotBlank(message = "state name cannot be blank")
    @Column(name = "state_name", nullable = false)
    private String stateName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "icon_id")
    private Media icon;

    @Enumerated(EnumType.STRING)
    @Column(name = "state_color", columnDefinition = "text", nullable = false)
    private Color stateColor;

    /**
     * State'in bağlı olduğu projeler (ProjectStateRelation).
     * cascade = ALL: State silinince proje ilişkileri de silinir
     * orphanRemoval = true: İlişki koparılınca relation silinir
     */
    @OneToMany(mappedBy = "state", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProjectStateRelation> projectStateRelations = new ArrayList<>();

    @Override
    public State cloneEntity() {
        return State.builder()
                .stateName(this.stateName)
                .stateColor(this.stateColor)
                .icon(this.icon)
                .build();
    }
}