package org.pepello.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.pepello.entities.baseClasses.UpdatedEntity;

@Getter
@Setter
@Entity
@Table(name = "messages")
@AttributeOverride(name = "id", column = @Column(name = "message_id", nullable = false, updatable = false))
public class Message extends UpdatedEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_id", nullable = false)
    private Chat chat;

    @Column(name = "text")
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "media_id")
    private Media mediaId;
}