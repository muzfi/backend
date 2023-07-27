package com.example.muzfi.Controller;

import com.example.muzfi.Model.Comment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.muzfi.Services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping
    public ResponseEntity<?> getAllComments() {
        try {
            Optional<List<Comment>> comments = commentService.getAllComments();

            if (comments.isPresent()) {
                return new ResponseEntity<>(comments, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No Comments Available", HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("an unknown error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<?> getCommentsByPostId(@PathVariable("postId") Integer postId) {
        try {
            Optional<List<Comment>> comments = commentService.getCommentsByPostId(postId);

            if (comments.isPresent()) {
                return new ResponseEntity<>(comments, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No Comments Available", HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("an unknown error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<?> createComment(@RequestBody Comment comment) {
        try {
            Comment createdComment = commentService.createComment(comment);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdComment);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<?> updateComment(@PathVariable("commentId") Integer commentId, @RequestBody Comment comment) {
        try {
            Comment updatedComment = commentService.updateComment(commentId, comment);
            if (updatedComment != null) {
                return new ResponseEntity<>(updatedComment, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No Comment Found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("an unknown error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Integer commentId) {
        try {
            commentService.deleteComment(commentId);
            return ResponseEntity.noContent().build();
        } catch (Exception ex) {
            return new ResponseEntity<>("an unknown error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}