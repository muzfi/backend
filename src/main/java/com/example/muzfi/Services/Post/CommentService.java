package com.example.muzfi.Services.Post;

import com.example.muzfi.Dto.PostDto.CommentCreateDto;
import com.example.muzfi.Dto.PostDto.CommentDetailsDto;
import com.example.muzfi.Dto.PostDto.CommentReplyDto;
import com.example.muzfi.Dto.PostDto.CommentUpdateDto;
import com.example.muzfi.Model.Post.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    Optional<Comment> getCommentById(String commentId);

    Optional<CommentDetailsDto> getCommentDetailsById(String commentId);

    Optional<List<CommentDetailsDto>> getCommentsByPostId(String postId);

    Optional<Integer> getPostCommentCount(String postId);

    Optional<Comment> createComment(CommentCreateDto commentDto);

    Optional<CommentDetailsDto> createReplyComment(CommentReplyDto replyDto);

    Optional<String> editComment(CommentUpdateDto updateDto);

    Optional<String> deleteComment(String commentId);

    Optional<List<String>> getCommentIdsByPostId(String postId);

    void deleteCommentsByIds(List<String> idList);
}
