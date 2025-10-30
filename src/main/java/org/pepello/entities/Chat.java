package org.pepello.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.pepello.entities.baseClasses.UpdatedEntity;

@Getter
@Setter
@Entity
@Table(name = "chats")
@AttributeOverride(name = "id", column = @Column(name = "chat_id", nullable = false, updatable = false))
public class Chat extends UpdatedEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;
}