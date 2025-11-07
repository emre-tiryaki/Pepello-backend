package org.pepello.controller;

import org.pepello.common.controller.ICrudEndpoints;
import org.pepello.dto.comment.CommentCreateRequest;
import org.pepello.dto.comment.CommentUpdateRequest;
import org.pepello.dto.comment.DtoComment;

public interface ICommentController extends ICrudEndpoints<DtoComment, CommentCreateRequest, CommentUpdateRequest> {
}
