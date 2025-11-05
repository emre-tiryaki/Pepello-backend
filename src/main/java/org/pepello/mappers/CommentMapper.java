package org.pepello.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.pepello.dto.comment.DtoComment;
import org.pepello.entities.Comment;

@Mapper(componentModel = "spring")
public interface CommentMapper extends BaseMapper<Comment, DtoComment> {
    @Override
    @Mapping(source = "id", target = "id")
    DtoComment toDto(Comment entity);

    @Override
    @Mapping(source = "id", target = "id")
    Comment fromDto(DtoComment dto);
}
