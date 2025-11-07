package org.pepello.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.pepello.common.baseEntities.CreatedEntity;
import org.pepello.entities.enums.MediaType;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "media")
@AttributeOverride(name = "id", column = @Column(name = "media_id", nullable = false, updatable = false))
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Media extends CreatedEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "media_type", nullable = false)
    private MediaType mediaType;

    @Column(name = "media_size", nullable = false)
    private BigDecimal mediaSize;

    @NotNull(message = "media should have a URL")
    @NotBlank(message = "media should have a URL")
    @Column(name = "media_url", nullable = false)
    private String mediaUrl;
}