package org.pepello.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.pepello.common.baseEntities.CreatedEntity;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "roles")
@AttributeOverride(name = "id", column = @Column(name = "role_id", nullable = false, updatable = false))
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Role extends CreatedEntity {

    @NotNull(message = "roles should have a name")
    @NotBlank(message = "roles should have a valid name")
    @Column(name = "role_name", nullable = false)
    private String roleName;

    @NotBlank(message = "role description cannot be blank")
    @Column(name = "role_description")
    private String roleDescription;

    /**
     * Role'e atanmış kullanıcılar (RoleUserRelation).
     * cascade = ALL: Role silinince kullanıcı ilişkileri de silinir
     * orphanRemoval = true: İlişki koparılınca relation silinir
     */
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<RoleUserRelation> roleUserRelations = new ArrayList<>();

    /**
     * Role'e atanmış team'ler (RoleTeamRelation).
     * cascade = ALL: Role silinince team ilişkileri de silinir
     * orphanRemoval = true: İlişki koparılınca relation silinir
     */
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<RoleTeamRelation> roleTeamRelations = new ArrayList<>();

}