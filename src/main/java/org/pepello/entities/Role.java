package org.pepello.entities;

import jakarta.persistence.*;
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

    @Column(name = "role_name", nullable = false, length = Integer.MAX_VALUE)
    private String roleName;

    @Column(name = "role_description", length = Integer.MAX_VALUE)
    private String roleDescription;

}