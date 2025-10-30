package org.pepello.repository;

import org.pepello.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MessageRepostory extends JpaRepository<Message, UUID> {
}
