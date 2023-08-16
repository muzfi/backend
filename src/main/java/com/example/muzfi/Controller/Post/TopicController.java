package com.example.muzfi.Controller.Post;

import com.example.muzfi.Dto.PostDto.TopicCreateDto;
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
}
