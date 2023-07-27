package com.example.muzfi.Services;

import com.example.muzfi.Model.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    Optional<List<Comment>> getAllComments();

    Optional<List<Comment>> getCommentsByPostId(Integer postId);

    Comment createComment(Comment comment);

    Comment updateComment(Integer commentId, Comment comment);

    void deleteComment(Integer commentId);
}
