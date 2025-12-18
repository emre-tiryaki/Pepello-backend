package org.pepello.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.pepello.common.baseEntities.UpdatedEntity;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * Team'e ait chat'ler.
     * cascade = ALL: Team silinince chat'ler de silinir
     * orphanRemoval = true: İlişki koparılınca chat silinir
     */
    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Chat> chats = new ArrayList<>();

    /**
     * Team'deki kullanıcılar (UserTeamRelation).
     * cascade = ALL: Team silinince kullanıcı ilişkileri de silinir
     * orphanRemoval = true: İlişki koparılınca relation silinir
     */
    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserTeamRelation> userTeamRelations = new ArrayList<>();

    /**
     * Team'deki roller (RoleTeamRelation).
     * cascade = ALL: Team silinince rol ilişkileri de silinir
     * orphanRemoval = true: İlişki koparılınca relation silinir
     */
    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RoleTeamRelation> roleTeamRelations = new ArrayList<>();

    /**
     * Team'in projeleri (TeamProjectRelation).
     * cascade = ALL: Team silinince proje ilişkileri de silinir
     * orphanRemoval = true: İlişki koparılınca relation silinir
     */
    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TeamProjectRelation> teamProjectRelations = new ArrayList<>();

}