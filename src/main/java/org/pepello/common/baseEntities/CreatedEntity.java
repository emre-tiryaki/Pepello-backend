package org.pepello.common.baseEntities;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.EqualsAndHashCode;

import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;

@MappedSuperclass
@Data
@EqualsAndHashCode(callSuper=true)
public abstract class CreatedEntity extends BaseEntity {
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    protected OffsetDateTime createdAt;
}
