package com.example.muzfi.Services;

import com.example.muzfi.Model.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    Optional<List<Comment>> getAllComments();

    Comment createComment(Comment comment);

    Comment updateComment(String commentId, Comment comment);

    void deleteComment(String commentId);
}
