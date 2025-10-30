package org.pepello.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.pepello.entities.baseClasses.CreatedEntity;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "roles")
@AttributeOverride(name = "id", column = @Column(name = "role_id", nullable = false, updatable = false))
public class Role extends CreatedEntity {

    @NotNull(message = "roles should have a name")
    @NotBlank(message = "roles should have a valid name")
    @Column(name = "role_name", nullable = false, length = Integer.MAX_VALUE)
    private String roleName;

    @NotBlank(message = "role description cannot be blank")
    @Column(name = "role_description", length = Integer.MAX_VALUE)
    private String roleDescription;

}