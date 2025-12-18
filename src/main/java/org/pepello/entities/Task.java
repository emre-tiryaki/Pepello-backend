package org.pepello.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.pepello.common.baseEntities.UpdatedEntity;
import org.pepello.common.entity.Prototype;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tasks")
@AttributeOverride(name = "id", column = @Column(name = "task_id", nullable = false, updatable = false))
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Task extends UpdatedEntity implements Prototype<Task> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_id", nullable = false)
    private State state;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "media_id")
    private Media media;

    @NotNull(message = "tasks should have a title")
    @NotBlank(message = "task title cannot be null")
    @Column(name = "task_title", nullable = false)
    private String taskTitle;

    @NotBlank(message = "task title cannot be blank")
    @Column(name = "task_description")
    private String taskDescription;

    @ColumnDefault("false")
    @Column(name = "is_finished", nullable = false)
    private Boolean isFinished = false;

    /**
     * Task'a atanmış kullanıcılar (TaskAsigneeRelation).
     * cascade = ALL: Task silinince atamaları da sil
     * orphanRemoval = true: İlişki koparılınca atama silinir
     */
    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TaskAsigneeRelation> taskAssigneeRelations = new ArrayList<>();

    /**
     * Task'a yapılan yorumlar.
     * cascade = ALL: Task silinince yorumlar da silinir
     * orphanRemoval = true: İlişki koparılınca yorum silinir
     */
    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @Override
    public Task cloneEntity() {
        return Task.builder()
                .project(this.project)
                .state(this.state)
                .media(this.media)
                .taskTitle(this.taskTitle)
                .taskDescription(this.taskDescription)
                .isFinished(false)
                .build();
    }
}