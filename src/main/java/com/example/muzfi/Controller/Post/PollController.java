package com.example.muzfi.Controller.Post;

import com.example.muzfi.Dto.PostDto.PollCreateDto;
import com.example.muzfi.Model.Post.Poll;
import com.example.muzfi.Services.AuthService;
import com.example.muzfi.Services.Post.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/polls")
public class PollController {
    private final AuthService authService;

    private final PollService pollService;

    @Autowired
    public PollController(AuthService authService, PollService pollService) {
        this.authService = authService;
        this.pollService = pollService;
    }

    @PreAuthorize("hasAuthority('Muzfi_Member')")
    @PostMapping
    public ResponseEntity<?> createPoll(@RequestBody PollCreateDto pollDto) {
        try {
            String loggedInUserId = pollDto.getAuthorId();

            boolean isLoggedInUser = authService.isLoggedInUser(loggedInUserId);

            if (!isLoggedInUser) {
                return new ResponseEntity<>("Access denied: You are not eligible to perform this action.", HttpStatus.UNAUTHORIZED);
            }

            Optional<?> poll = pollService.createPoll(pollDto);

            if (poll.isPresent()) {
                return new ResponseEntity<>(poll.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Creat poll failed", HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("an unknown error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllPolls() {
        try {
            Optional<List<Poll>> pollList = pollService.getAllPolls();

            if (pollList.isPresent()) {
                return new ResponseEntity<>(pollList.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Cannot retrieve polls", HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("an unknown error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/{pollId}")
    public ResponseEntity<?> getPollById(@PathVariable("pollId") String pollId) {
        try {
            Optional<Poll> poll = pollService.getPollById(pollId);

            if (poll.isPresent()) {
                return new ResponseEntity<>(poll.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Cannot retrieve poll", HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("an unknown error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
