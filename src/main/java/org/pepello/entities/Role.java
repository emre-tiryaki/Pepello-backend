package org.pepello.entities;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.pepello.common.baseEntities.CreatedEntity;

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

}