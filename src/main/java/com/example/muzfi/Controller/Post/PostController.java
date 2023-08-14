package com.example.muzfi.Controller.Post;

import com.example.muzfi.Dto.PostDto.PostDetailsDto;
import com.example.muzfi.Model.Post.Post;
import com.example.muzfi.Services.Post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public ResponseEntity<?> getAllPosts() {
        try {
            Optional<List<PostDetailsDto>> postList = postService.getAllPosts();

            if (postList.isPresent()) {
                return new ResponseEntity<>(postList.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Cannot retrieve posts", HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("an unknown error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{postId}")
    public ResponseEntity<?> getPost(@PathVariable("postId") String postId) {
        try {
            Optional<PostDetailsDto> post = postService.getPostById(postId);

            if (post.isPresent()) {
                return new ResponseEntity<>(post.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Cannot retrieve this post data", HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("an unknown error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Post> savePost(@RequestBody Post post) {
        return ResponseEntity.ok(postService.savePost(post));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable String id) {
        postService.deletePost(id);
        return ResponseEntity.ok().build();
    }
}