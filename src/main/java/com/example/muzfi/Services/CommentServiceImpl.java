package com.example.muzfi.Services;

import com.example.muzfi.Model.Comment;
import com.example.muzfi.Repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {


    private final CommentRepository commentRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Optional<List<Comment>> getAllComments() {
        return Optional.of(commentRepository.findAll());
    }

    @Override
    public Optional<List<Comment>> getCommentsByPostId(String postId) {
        return Optional.ofNullable(commentRepository.findAllByPostId(postId));
    }

    @Override
    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Comment updateComment(String commentId, Comment updatedComment) {
        Comment existingComment = commentRepository.findById(commentId).orElse(null);
        if (existingComment != null) {
            existingComment.setText(updatedComment.getText());

            //TODO: update with user date and time
            existingComment.setUpdatedDateTime(LocalDateTime.now());
            return commentRepository.save(existingComment);
        }
        return null;
    }

    @Override
    public void deleteComment(String commentId) {
        commentRepository.deleteById(commentId);
    }
}
