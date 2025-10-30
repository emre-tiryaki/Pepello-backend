package org.pepello.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.pepello.entities.baseClasses.UpdatedEntity;

@Getter
@Setter
@Entity
@Table(name = "comments")
@AttributeOverride(name = "id", column = @Column(name = "comment_id", nullable = false, updatable = false))
public class Comment extends UpdatedEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull(message = "comment cannot be null")
    @NotBlank(message = "comment should have a text")
    @Column(name = "text", nullable = false)
    private String text;

}