package org.pepello.entities;

import jakarta.persistence.*;
import lombok.*;
import org.pepello.common.baseEntities.UpdatedEntity;

@Getter
@Setter
@Entity
@Table(name = "chats")
@AttributeOverride(name = "id", column = @Column(name = "chat_id", nullable = false, updatable = false))
@AllArgsConstructor
@NoArgsConstructor
@Builder
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