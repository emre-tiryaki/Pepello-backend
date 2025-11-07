package org.pepello.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.pepello.common.baseEntities.UpdatedEntity;

@Getter
@Setter
@Entity
@Table(name = "teams")
@AttributeOverride(name = "id", column = @Column(name = "team_id", nullable = false, updatable = false))
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Team extends UpdatedEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "icon_id", nullable = false)
    private Media icon;

    @NotNull(message = "team name cannot be null")
    @NotBlank(message = "team name cannot be blank")
    @Column(name = "team_name", nullable = false)
    private String teamName;

    @NotBlank(message = "team description cannot be blank")
    @Column(name = "description")
    private String description;

}