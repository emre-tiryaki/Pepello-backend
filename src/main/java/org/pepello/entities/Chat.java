package org.pepello.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "icon_id")
    private Media icon;

    @Column(name = "chat_name", nullable = false)
    private String name;

    @Column(name = "chat_description")
    private String description;
}