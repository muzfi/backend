package com.example.muzfi.Controller.Post;

import com.example.muzfi.Dto.PostDto.LikedUserDto;
import com.example.muzfi.Dto.PostDto.PostCreateDto;
import com.example.muzfi.Dto.PostDto.PostDetailsDto;
import com.example.muzfi.Model.Post.Like;
import com.example.muzfi.Services.AuthService;
import com.example.muzfi.Services.Post.LikeService;
import com.example.muzfi.Services.Post.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final AuthService authService;

    private final PostService postService;

    private final LikeService likeService;

    @Autowired
    public PostController(AuthService authService, PostService postService, LikeService likeService) {
        this.authService = authService;
        this.postService = postService;
        this.likeService = likeService;
    }

    @PreAuthorize("hasAuthority('Muzfi_Member')")
    @PostMapping("/create-post")
    public ResponseEntity<?> createPost(@Valid @RequestBody PostCreateDto postDto) {
        try {
            String loggedInUserId = postDto.getAuthorId();

            boolean isLoggedInUser = authService.isLoggedInUser(loggedInUserId);

            if (!isLoggedInUser) {
                return new ResponseEntity<>("Access denied: You are not eligible to perform this action.", HttpStatus.UNAUTHORIZED);
            }

            Optional<PostDetailsDto> createdPost = postService.createPost(postDto);

            if (createdPost.isPresent()) {
                return new ResponseEntity<>(createdPost.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Create post failed", HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("An unknown error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
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

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getPostsByUserId(@PathVariable("userId") String userId) {

        try {
            Optional<List<PostDetailsDto>> posts = postService.getPostsByUserId(userId);

            if (posts.isPresent()) {
                return new ResponseEntity<>(posts.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("There is no posts to show", HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("an unknown error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAuthority('Muzfi_Member')")
    @GetMapping("/user/draft/{userId}")
    public ResponseEntity<?> getDraftPostsByUserId(@PathVariable("userId") String userId) {
        try {
            boolean isLoggedInUser = authService.isLoggedInUser(userId);

            if (!isLoggedInUser) {
                return new ResponseEntity<>("Access denied: You are not eligible to retrieve this posts.", HttpStatus.UNAUTHORIZED);
            }

            Optional<List<PostDetailsDto>> posts = postService.getDraftPostsByUserId(userId);

            if (posts.isPresent()) {
                return new ResponseEntity<>(posts.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("There is no draft posts to show", HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("an unknown error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAuthority('Muzfi_Member')")
    @PutMapping("/user/publish-draft/{postId}")
    public ResponseEntity<?> publishDraftPost(@PathVariable("postId") String postId, @RequestParam("userId") String userId) {
        try {
            boolean isLoggedInUser = authService.isLoggedInUser(userId);

            if (!isLoggedInUser) {
                return new ResponseEntity<>("Access denied: You are not eligible to perform this action.", HttpStatus.UNAUTHORIZED);
            }

            Optional<PostDetailsDto> posts = postService.publishDraftPost(postId);

            if (posts.isPresent()) {
                return new ResponseEntity<>(posts.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Publishing draft post failed", HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("an unknown error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PreAuthorize("hasAuthority('Muzfi_Member')")
    @GetMapping("/{postId}/like/{userId}")
    public ResponseEntity<?> addLike(@PathVariable("postId") String postId, @PathVariable("userId") String userId) {
        try {
            boolean isLoggedInUser = authService.isLoggedInUser(userId);

            if (!isLoggedInUser) {
                return new ResponseEntity<>("Access denied: You are not eligible to perform this action.", HttpStatus.UNAUTHORIZED);
            }

            Optional<Like> like = likeService.createLike(postId, userId);

            if (like.isPresent()) {
                return new ResponseEntity<>("Like added to the post", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Cannot like this post", HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("an unknown error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAuthority('Muzfi_Member')")
    @GetMapping("/{postId}/remove-like/{userId}")
    public ResponseEntity<?> RemoveLike(@PathVariable("postId") String postId, @PathVariable("userId") String userId) {
        try {
            boolean isLoggedInUser = authService.isLoggedInUser(userId);

            if (!isLoggedInUser) {
                return new ResponseEntity<>("Access denied: You are not eligible to perform this action.", HttpStatus.UNAUTHORIZED);
            }

            Optional<String> res = likeService.removeLike(postId, userId);

            if (res.isPresent()) {
                return new ResponseEntity<>(res.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Cannot remove like from this post", HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("an unknown error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{postId}/likes")
    public ResponseEntity<?> getPostLikes(@PathVariable("postId") String postId) {
        try {
            Optional<List<LikedUserDto>> likedUsers = likeService.getLikedUsersByPostId(postId);

            if (likedUsers.isPresent()) {
                return new ResponseEntity<>(likedUsers.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("There is no liked users", HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("an unknown error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAuthority('Muzfi_Member')")
    @DeleteMapping("/{postId}")
    public ResponseEntity<?> deletePostById(@PathVariable("postId") String postId, @RequestParam("userId") String userId) {
        try {
            boolean isLoggedInUser = authService.isLoggedInUser(userId);

            if (!isLoggedInUser) {
                return new ResponseEntity<>("Access denied: You are not eligible to perform this action.", HttpStatus.UNAUTHORIZED);
            }

            Optional<String> res = postService.deletePost(postId);

            if (res.isPresent()) {
                return new ResponseEntity<>(res.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Cannot delete this post", HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("an unknown error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}