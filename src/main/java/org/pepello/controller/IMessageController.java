package org.pepello.controller;

import org.pepello.common.controller.ICrudEndpoints;
import org.pepello.dto.message.DtoMessage;
import org.pepello.dto.message.MessageCreateRequest;
import org.pepello.dto.message.MessageUpdateRequest;

public interface IMessageController extends ICrudEndpoints<DtoMessage, MessageCreateRequest, MessageUpdateRequest> {
}
