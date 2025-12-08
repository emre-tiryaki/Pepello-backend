package org.pepello.controller.Impl;

import org.pepello.common.ICrud;
import org.pepello.common.controller.BaseCrudController;
import org.pepello.common.mapper.BaseMapper;
import org.pepello.controller.IMessageController;
import org.pepello.dto.message.DtoMessage;
import org.pepello.dto.message.MessageCreateRequest;
import org.pepello.dto.message.MessageUpdateRequest;
import org.pepello.entities.Message;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
public class MessageControllerImpl extends BaseCrudController<Message, DtoMessage, MessageCreateRequest, MessageUpdateRequest> implements IMessageController {
    public MessageControllerImpl(ICrud<Message, MessageCreateRequest, MessageUpdateRequest> service, BaseMapper<Message, DtoMessage> mapper) {
        super(service, mapper);
    }
}
