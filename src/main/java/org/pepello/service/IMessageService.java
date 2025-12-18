package org.pepello.service;

import org.pepello.common.ICrud;
import org.pepello.dto.message.MessageCreateRequest;
import org.pepello.dto.message.MessageUpdateRequest;
import org.pepello.entities.Message;

import java.util.List;
import java.util.UUID;

public interface IMessageService extends ICrud<Message, MessageCreateRequest, MessageUpdateRequest> {
    List<Message> getChatMessages(UUID chatId);
}
