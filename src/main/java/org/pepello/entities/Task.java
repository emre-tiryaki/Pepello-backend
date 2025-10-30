package org.pepello.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.pepello.entities.baseClasses.UpdatedEntity;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "tasks")
@AttributeOverride(name = "id", column = @Column(name = "task_id", nullable = false, updatable = false))
public class Task extends UpdatedEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_id", nullable = false)
    private State state;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "media_id", nullable = false)
    private Media media;

    @NotNull(message = "tasks should have a title")
    @NotBlank(message = "task title cannot be null")
    @Column(name = "task_title", nullable = false, length = Integer.MAX_VALUE)
    private String taskTitle;

    @NotBlank(message = "task title cannot be blank")
    @Column(name = "task_description", length = Integer.MAX_VALUE)
    private String taskDescription;

    @ColumnDefault("false")
    @Column(name = "is_finished", nullable = false)
    private Boolean isFinished = false;

}