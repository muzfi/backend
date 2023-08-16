package com.example.muzfi.Controller.Post;

import com.example.muzfi.Dto.PostDto.CommentCreateDto;
import com.example.muzfi.Dto.PostDto.CommentDetailsDto;
import com.example.muzfi.Dto.PostDto.CommentReplyDto;
import com.example.muzfi.Dto.PostDto.CommentUpdateDto;
import com.example.muzfi.Model.Post.Comment;
import com.example.muzfi.Services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.example.muzfi.Services.Post.CommentService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    private final CommentService commentService;

    private final AuthService authService;

    @Autowired
    public CommentController(CommentService commentService, AuthService authService) {
        this.commentService = commentService;
        this.authService = authService;
    }


    @GetMapping("/post/{postId}")
    public ResponseEntity<?> getCommentsByPostId(@PathVariable("postId") String postId) {
        try {
            Optional<List<CommentDetailsDto>> comments = commentService.getCommentsByPostId(postId);

            if (comments.isPresent()) {
                return new ResponseEntity<>(comments.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No Comments Available for this post", HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("an unknown error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAuthority('Muzfi_Member')")
    @PostMapping
    public ResponseEntity<?> createComment(@RequestBody CommentCreateDto commentDto) {
        try {
            String loggedInUserId = commentDto.getUserId();

            boolean isLoggedInUser = authService.isLoggedInUser(loggedInUserId);

            if (!isLoggedInUser) {
                return new ResponseEntity<>("Access denied: You are not eligible to perform this action.", HttpStatus.UNAUTHORIZED);
            }

            Optional<Comment> commentOptional = commentService.createComment(commentDto);

            if (commentOptional.isPresent()) {
                return new ResponseEntity<>(commentOptional.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Creat comment failed", HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("an unknown error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAuthority('Muzfi_Member')")
    @PostMapping("/reply")
    public ResponseEntity<?> createReplyComment(@RequestBody CommentReplyDto replyDto) {
        try {
            String loggedInUserId = replyDto.getUserId();

            boolean isLoggedInUser = authService.isLoggedInUser(loggedInUserId);

            if (!isLoggedInUser) {
                return new ResponseEntity<>("Access denied: You are not eligible to perform this action.", HttpStatus.UNAUTHORIZED);
            }

            Optional<CommentDetailsDto> commentOptional = commentService.createReplyComment(replyDto);

            if (commentOptional.isPresent()) {
                return new ResponseEntity<>(commentOptional.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Creat comment reply failed", HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("an unknown error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAuthority('Muzfi_Member')")
    @PutMapping
    public ResponseEntity<?> updateComment(@RequestBody CommentUpdateDto commentDto) {
        try {
            Optional<Comment> commentOptional = commentService.getCommentById(commentDto.getId());

            if (commentOptional.isEmpty()) {
                return new ResponseEntity<>("No existing comment found", HttpStatus.NOT_FOUND);
            }

            boolean isLoggedInUser = authService.isLoggedInUser(commentOptional.get().getUserId());

            if (!isLoggedInUser) {
                return new ResponseEntity<>("Access denied: You are not eligible to perform this action.", HttpStatus.UNAUTHORIZED);
            }

            Optional<String> commentUpdated = commentService.editComment(commentDto);

            if (commentUpdated.isPresent()) {
                return new ResponseEntity<>(commentUpdated.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Edit comment failed", HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("an unknown error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAuthority('Muzfi_Member')")
    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable("commentId") String commentId) {
        try {
            Optional<Comment> commentOptional = commentService.getCommentById(commentId);

            if (commentOptional.isEmpty()) {
                return new ResponseEntity<>("No existing comment found", HttpStatus.NOT_FOUND);
            }

            boolean isLoggedInUser = authService.isLoggedInUser(commentOptional.get().getUserId());

            if (!isLoggedInUser) {
                return new ResponseEntity<>("Access denied: You are not eligible to perform this action.", HttpStatus.UNAUTHORIZED);
            }

            Optional<String> commentDeleted = commentService.deleteComment(commentId);

            if (commentDeleted.isPresent()) {
                return new ResponseEntity<>(commentDeleted.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Delete comment failed", HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("an unknown error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
