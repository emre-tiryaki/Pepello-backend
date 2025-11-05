package org.pepello.controller.Impl;

import org.pepello.controller.ICommentController;
import org.pepello.dto.comment.CommentCreateRequest;
import org.pepello.dto.comment.CommentUpdateRequest;
import org.pepello.dto.comment.DtoComment;
import org.pepello.mappers.CommentMapper;
import org.pepello.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/comment")
public class CommentControllerImpl implements ICommentController {
    @Autowired
    private ICommentService commentService;
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<DtoComment> getAll() {
        return commentService.getAll().stream().map(commentMapper::toDto).toList();
    }

    @Override
    public DtoComment getById(UUID id) {
        return commentMapper.toDto(commentService.getById(id));
    }

    @Override
    public DtoComment create(CommentCreateRequest createDto) {
        return commentMapper.toDto(commentService.create(createDto));
    }

    @Override
    public DtoComment update(UUID id, CommentUpdateRequest updateDto) {
        return commentMapper.toDto(commentService.update(id, updateDto));
    }

    @Override
    public void delete(UUID id) {
        commentService.delete(id);
    }
}
