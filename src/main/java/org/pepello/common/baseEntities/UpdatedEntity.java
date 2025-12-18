package org.pepello.common.baseEntities;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;

@MappedSuperclass
@Data
public abstract class UpdatedEntity extends CreatedEntity {
    @UpdateTimestamp
    @Column(name = "updated_at")
    protected OffsetDateTime updatedAt;
}
