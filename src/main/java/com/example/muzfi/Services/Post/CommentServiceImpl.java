package com.example.muzfi.Services.Post;

import com.example.muzfi.Dto.PostDto.*;
import com.example.muzfi.Model.Post.Comment;
import com.example.muzfi.Repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    public Optional<Comment> getCommentById(String commentId) {
        return commentRepository.findById(commentId);
    }

    @Override
    public Optional<CommentDetailsDto> getCommentDetailsById(String commentId) {
        Optional<Comment> commentOptional = commentRepository.findById(commentId);

        if (commentOptional.isPresent()) {
            Comment comment = commentOptional.get();

            //Create child comment list
            List<String> idList = comment.getReplyCommentIds();
            List<CommentReplyDetailsDto> commentReplyList = new ArrayList<>();

            if (idList != null) {
                for (String id : idList) {
                    Optional<Comment> commentOpt = commentRepository.findById(id);

                    if (commentOpt.isPresent()) {
                        Comment commentReply = commentOpt.get();
                        CommentReplyDetailsDto commentReplyDto = new CommentReplyDetailsDto();

                        commentReplyDto.setId(commentReply.getId());
                        commentReplyDto.setUserId(commentReply.getUserId());
                        commentReplyDto.setText(commentReply.getText());
                        commentReplyDto.setCreatedDateTime(commentReply.getCreatedDateTime());
                        commentReplyDto.setUpdatedDateTime(commentReply.getUpdatedDateTime());

                        commentReplyList.add(commentReplyDto);
                    }
                }
            }


            //Create parent comment
            CommentDetailsDto commentDetails = new CommentDetailsDto();

            commentDetails.setId(comment.getId());
            commentDetails.setPostId(comment.getPostId());
            commentDetails.setUserId(comment.getUserId());
            commentDetails.setText(comment.getText());
            commentDetails.setReplyComments(commentReplyList);
            commentDetails.setCreatedDateTime(comment.getCreatedDateTime());
            commentDetails.setUpdatedDateTime(comment.getUpdatedDateTime());

            return Optional.of(commentDetails);
        }

        return Optional.empty();
    }

    @Override
    public Optional<List<CommentDetailsDto>> getCommentsByPostId(String postId) {

        List<Comment> commentList = commentRepository.findAllByPostIdAndIsParentComment(postId, true);

        if (commentList.isEmpty()) {
            return Optional.empty();
        }

        List<CommentDetailsDto> postComments = new ArrayList<>();

        for (Comment comment : commentList) {
            Optional<CommentDetailsDto> commentDtoOpt = getCommentDetailsById(comment.getId());

            commentDtoOpt.ifPresent(postComments::add);
        }

        return Optional.of(postComments);
    }

    @Override
    public Optional<Integer> getPostCommentCount(String postId) {

        return commentRepository.countAllByPostId(postId);
    }

    @Override
    public Optional<Comment> createComment(CommentCreateDto commentDto) {
        Comment newComment = new Comment();

        newComment.setPostId(commentDto.getPostId());
        newComment.setUserId(commentDto.getUserId());
        newComment.setText(commentDto.getText());
        newComment.setIsParentComment(true);
        newComment.setCreatedDateTime(commentDto.getCreatedDateTime());
        newComment.setUpdatedDateTime(commentDto.getCreatedDateTime());

        return Optional.of(commentRepository.save(newComment));
    }

    @Override
    public Optional<CommentDetailsDto> createReplyComment(CommentReplyDto replyDto) {
        Comment replyComment = new Comment();

        replyComment.setPostId(replyDto.getPostId());
        replyComment.setUserId(replyDto.getUserId());
        replyComment.setText(replyDto.getText());
        replyComment.setIsParentComment(false);
        replyComment.setCreatedDateTime(replyDto.getCreatedDateTime());
        replyComment.setUpdatedDateTime(replyDto.getCreatedDateTime());

        Optional<Comment> commentOptional = getCommentById(replyDto.getCommentId());

        if (commentOptional.isPresent()) {
            Comment newComment = commentRepository.save(replyComment);
            Comment comment = commentOptional.get();

            List<String> replyIds = new ArrayList<>();
            if (comment.getReplyCommentIds() != null) {
                replyIds = comment.getReplyCommentIds();
            }
            replyIds.add(newComment.getId());

            comment.setReplyCommentIds(replyIds);

            commentRepository.save(comment);

            return getCommentDetailsById(comment.getId());
        }

        return Optional.empty();
    }

    @Override
    public Optional<String> editComment(CommentUpdateDto updateDto) {

        Optional<Comment> existingComment = getCommentById(updateDto.getId());

        if (existingComment.isPresent()) {
            Comment comment = existingComment.get();
            comment.setText(updateDto.getText());
            comment.setUpdatedDateTime(LocalDateTime.now());

            commentRepository.save(comment);

            return Optional.of("Comment updated");
        }

        return Optional.empty();
    }

    @Override
    public Optional<String> deleteComment(String commentId) {
        Optional<Comment> existingComment = getCommentById(commentId);

        if (existingComment.isPresent()) {
            List<String> replyIdsList = existingComment.get().getReplyCommentIds();

            if (replyIdsList != null) {
                for (String id : replyIdsList) {
                    commentRepository.deleteById(id);
                }
            }

            commentRepository.deleteById(commentId);

            return Optional.of("Comment Deleted");
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<String>> getCommentIdsByPostId(String postId) {
        List<Comment> postComments = commentRepository.findAllByPostId(postId);

        List<String>  commentList = new ArrayList<>();

        if (postComments.isEmpty()) {
            return Optional.empty();
        }

        for (Comment comment: postComments) {
            commentList.add(comment.getId());
        }

        return Optional.of(commentList);
    }

    @Override
    public void deleteCommentsByIds(List<String> idList) {
        commentRepository.deleteAllById(idList);
    }
}
