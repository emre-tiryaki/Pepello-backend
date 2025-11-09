package org.pepello.service.impl;

import jakarta.transaction.Transactional;
import org.pepello.dto.comment.CommentCreateRequest;
import org.pepello.dto.comment.CommentUpdateRequest;
import org.pepello.entities.Comment;
import org.pepello.entities.Task;
import org.pepello.entities.User;
import org.pepello.repository.CommentRepository;
import org.pepello.service.ICommentService;
import org.pepello.service.ITaskService;
import org.pepello.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class CommentServiceImpl implements ICommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private IUserService userService;
    @Autowired
    private ITaskService taskService;

    @Override
    public List<Comment> getAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comment getById(UUID id) {
        // TODO: Daha iyi bir hata mimarisi yapınca değiştirilecek
        if (id == null) {
            throw new RuntimeException("ID null olamaz");
        }

        return commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment bulunamadı: " + id));
    }

    @Override
    public Comment create(CommentCreateRequest createDto) {
        // TODO: Daha iyi bir hata mimarisi yapınca değiştirilecek
        if (createDto == null) {
            throw new RuntimeException("CreateDto null olamaz");
        }

        Comment newComment = Comment.builder()
                .task(taskService.getById(createDto.taskId()))
                .user(userService.getById(createDto.userId()))
                .text(createDto.text())
                .build();

        return commentRepository.save(newComment);
    }

    @Override
    public Comment update(UUID id, CommentUpdateRequest updateDto) {
        // TODO: Daha iyi bir hata mimarisi yapınca değiştirilecek
        if (id == null) {
            throw new RuntimeException("ID null olamaz");
        }
        if (updateDto == null) {
            throw new RuntimeException("UpdateDto null olamaz");
        }

        Comment existingComment = getById(id);

        if (updateDto.taskId() != null) {
            Task existingTask = taskService.getById(updateDto.taskId());
            existingComment.setTask(existingTask);
        }
        if (updateDto.userId() != null) {
            User existingUser = userService.getById(updateDto.userId());
            existingComment.setUser(existingUser);
        }
        if (updateDto.text() != null) existingComment.setText(updateDto.text());

        return commentRepository.save(existingComment);
    }

    @Override
    public void delete(UUID id) {
        // TODO: Daha iyi bir hata mimarisi yapınca değiştirilecek
        if (id == null) {
            throw new RuntimeException("ID null olamaz");
        }

        Comment existingComment = getById(id);

        commentRepository.delete(existingComment);
    }

    @Override
    public boolean exists(UUID id) {
        return commentRepository.existsById(id);
    }
}
