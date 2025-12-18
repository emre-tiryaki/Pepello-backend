package org.pepello.entities;

import jakarta.persistence.*;
import lombok.*;
import org.pepello.common.baseEntities.UpdatedEntity;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * Chat'e ait mesajlar.
     * cascade = ALL: Chat silinince mesajlar da silinir
     * orphanRemoval = true: İlişki koparılınca mesaj silinir
     */
    @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Message> messages = new ArrayList<>();
}