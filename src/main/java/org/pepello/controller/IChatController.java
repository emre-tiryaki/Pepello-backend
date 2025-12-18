package org.pepello.controller;

import org.pepello.dto.message.DtoMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

public interface IChatController {
    @GetMapping("/{chatId}/messages")
    List<DtoMessage> getMyChats(
            @PathVariable UUID chatId
    );
}
