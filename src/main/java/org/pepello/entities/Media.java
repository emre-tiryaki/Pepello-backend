package org.pepello.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.pepello.entities.baseClasses.CreatedEntity;
import org.pepello.entities.enums.MediaType;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "media")
@AttributeOverride(name = "id", column = @Column(name = "media_id", nullable = false, updatable = false))
public class Media extends CreatedEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "media_type", columnDefinition = "text", nullable = false)
    private MediaType mediaType;

    @Column(name = "media_size", nullable = false, precision = 10, scale = 2)
    private BigDecimal mediaSize;

    @NotNull(message = "media should have a URL")
    @NotBlank(message = "media should have a URL")
    @Column(name = "media_url", nullable = false)
    private String mediaUrl;
}