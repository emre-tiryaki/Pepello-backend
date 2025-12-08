package org.pepello.controller.Impl;

import org.pepello.common.ICrud;
import org.pepello.common.controller.BaseCrudController;
import org.pepello.common.mapper.BaseMapper;
import org.pepello.controller.ICommentController;
import org.pepello.dto.comment.CommentCreateRequest;
import org.pepello.dto.comment.CommentUpdateRequest;
import org.pepello.dto.comment.DtoComment;
import org.pepello.entities.Comment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
public class CommentControllerImpl extends BaseCrudController<Comment, DtoComment, CommentCreateRequest, CommentUpdateRequest> implements ICommentController {
    public CommentControllerImpl(ICrud<Comment, CommentCreateRequest, CommentUpdateRequest> service, BaseMapper<Comment, DtoComment> mapper) {
        super(service, mapper);
    }
}
