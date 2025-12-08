package org.pepello.service;

import org.pepello.common.ICrud;
import org.pepello.dto.message.MessageCreateRequest;
import org.pepello.dto.message.MessageUpdateRequest;
import org.pepello.entities.Message;

public interface IMessageService extends ICrud<Message, MessageCreateRequest, MessageUpdateRequest> {
}
