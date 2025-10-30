package org.pepello.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.pepello.entities.baseClasses.UpdatedEntity;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "projects")
@AttributeOverride(name = "id", column = @Column(name = "project_id", nullable = false, updatable = false))
public class Project extends UpdatedEntity {

    @Column(name = "project_name", nullable = false, length = Integer.MAX_VALUE)
    private String projectName;

    @Column(name = "project_description", length = Integer.MAX_VALUE)
    private String projectDescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "icon_id", nullable = false)
    private Media iconId;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

}