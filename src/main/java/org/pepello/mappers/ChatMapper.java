package org.pepello.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.pepello.common.mapper.BaseMapper;
import org.pepello.dto.chat.DtoChat;
import org.pepello.entities.Chat;

@Mapper(componentModel = "spring")
public interface ChatMapper extends BaseMapper<Chat, DtoChat> {
    @Override
    @Mapping(source = "id", target = "id")
    DtoChat toDto(Chat entity);

    @Override
    @Mapping(source = "id", target = "id")
    Chat fromDto(DtoChat dto);
}
