package org.pepello.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.pepello.common.mapper.BaseMapper;
import org.pepello.dto.media.DtoMedia;
import org.pepello.entities.Media;

@Mapper(componentModel = "spring")
public interface MediaMapper extends BaseMapper<Media, DtoMedia> {
    @Override
    @Mapping(source = "id", target = "id")
    DtoMedia toDto(Media entity);

    @Override
    @Mapping(source = "id", target = "id")
    Media fromDto(DtoMedia dto);
}
