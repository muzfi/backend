package com.example.muzfi.Controller.Post;

import com.example.muzfi.Dto.PostDto.PostDetailsDto;
import com.example.muzfi.Dto.PostDto.TopicCreateDto;
import com.example.muzfi.Dto.PostDto.TopicUpdateDto;
import com.example.muzfi.Model.Post.Topic;
import com.example.muzfi.Services.AuthService;
import com.example.muzfi.Services.Post.TopicService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/topics")
public class TopicController {

    private final AuthService authService;

    private final TopicService topicService;

    public TopicController(AuthService authService, TopicService topicService) {
        this.authService = authService;
        this.topicService = topicService;
    }

    @PreAuthorize("hasAuthority('Muzfi_Member')")
    @PostMapping
    public ResponseEntity<?> createTopic(@RequestBody TopicCreateDto topicDto) {
        try {
            String loggedInUserId = topicDto.getAuthorId();

            boolean isLoggedInUser = authService.isLoggedInUser(loggedInUserId);

            if (!isLoggedInUser) {
                return new ResponseEntity<>("Access denied: You are not eligible to perform this action.", HttpStatus.UNAUTHORIZED);
            }

            Optional<?> topic = topicService.createTopic(topicDto);

            if (topic.isPresent()) {
                return new ResponseEntity<>(topic.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Creat topic failed", HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("an unknown error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllTopics() {
        try {
            Optional<List<Topic>> topicList = topicService.getAllTopics();

            if (topicList.isPresent()) {
                return new ResponseEntity<>(topicList.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Cannot retrieve topics", HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("an unknown error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/{topicId}")
    public ResponseEntity<?> getTopicById(@PathVariable("topicId") String topicId) {
        try {
            Optional<Topic> topic = topicService.getTopicById(topicId);

            if (topic.isPresent()) {
                return new ResponseEntity<>(topic.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Cannot retrieve topic", HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("an unknown error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getTopicByUserId(@PathVariable("userId") String userId) {
        try {
            Optional<List<Topic>> topics = topicService.getTopicsByUserId(userId);

            if (topics.isPresent()) {
                return new ResponseEntity<>(topics.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Cannot retrieve topics", HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("an unknown error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAuthority('Muzfi_Member')")
    @GetMapping("/user/draft/{userId}")
    public ResponseEntity<?> getDraftTopicsByUserId(@PathVariable("userId") String userId) {
        try {
            boolean isLoggedInUser = authService.isLoggedInUser(userId);

            if (!isLoggedInUser) {
                return new ResponseEntity<>("Access denied: You are not eligible to retrieve this posts.", HttpStatus.UNAUTHORIZED);
            }

            Optional<List<Topic>> topics = topicService.getDraftTopicsByUserId(userId);

            if (topics.isPresent()) {
                return new ResponseEntity<>(topics.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Cannot retrieve draft topics", HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("an unknown error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAuthority('Muzfi_Member')")
    @PutMapping
    public ResponseEntity<?> updateTopic(@RequestBody TopicUpdateDto topicUpdateDto) {
        try {
            Optional<Topic> topicOptional = topicService.getTopicById(topicUpdateDto.getId());

            if (topicOptional.isEmpty()) {
                return new ResponseEntity<>("No existing topic found", HttpStatus.NOT_FOUND);
            }

            boolean isLoggedInUser = authService.isLoggedInUser(topicOptional.get().getAuthorId());

            if (!isLoggedInUser) {
                return new ResponseEntity<>("Access denied: You are not eligible to perform this action.", HttpStatus.UNAUTHORIZED);
            }

            Optional<PostDetailsDto> postUpdated = topicService.updateTopic(topicUpdateDto);

            if (postUpdated.isPresent()) {
                return new ResponseEntity<>(postUpdated.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Update topic failed", HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("an unknown error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
