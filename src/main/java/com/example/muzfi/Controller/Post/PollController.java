package com.example.muzfi.Controller.Post;

import com.example.muzfi.Dto.PostDto.PollCreateDto;
import com.example.muzfi.Dto.PostDto.PollDetailsDto;
import com.example.muzfi.Dto.PostDto.PollUpdateDto;
import com.example.muzfi.Dto.PostDto.PostDetailsDto;
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
            Optional<List<PollDetailsDto>> pollList = pollService.getAllPolls();

            if (pollList.isPresent()) {
                return new ResponseEntity<>(pollList.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Cannot retrieve polls", HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("an unknown error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getAllPollsByUserId(@PathVariable("userId") String userId) {
        try {
            Optional<List<PollDetailsDto>> pollList = pollService.getPollsByUserId(userId);

            if (pollList.isPresent()) {
                return new ResponseEntity<>(pollList.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Cannot retrieve polls", HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("an unknown error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAuthority('Muzfi_Member')")
    @GetMapping("/user/draft/{userId}")
    public ResponseEntity<?> getDraftPollsByUserId(@PathVariable("userId") String userId) {
        try {
            boolean isLoggedInUser = authService.isLoggedInUser(userId);

            if (!isLoggedInUser) {
                return new ResponseEntity<>("Access denied: You are not eligible to retrieve this posts.", HttpStatus.UNAUTHORIZED);
            }

            Optional<List<PollDetailsDto>> pollList = pollService.getDraftPollsByUserId(userId);

            if (pollList.isPresent()) {
                return new ResponseEntity<>(pollList.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Cannot retrieve draft polls", HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("an unknown error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/{pollId}")
    public ResponseEntity<?> getPollById(@PathVariable("pollId") String pollId) {
        try {
            Optional<PollDetailsDto> poll = pollService.getPollById(pollId);

            if (poll.isPresent()) {
                return new ResponseEntity<>(poll.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Cannot retrieve poll", HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("an unknown error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAuthority('Muzfi_Member')")
    @GetMapping("/vote/{pollOptionId}/user/{userId}")
    public ResponseEntity<?> votePoll(@PathVariable("pollOptionId") String pollOptionId, @PathVariable("userId") String userId) {
        try {
            boolean isLoggedInUser = authService.isLoggedInUser(userId);

            if (!isLoggedInUser) {
                return new ResponseEntity<>("Access denied: You are not eligible to perform this action.", HttpStatus.UNAUTHORIZED);
            }

            Optional<String> vote = pollService.vote(pollOptionId, userId);

            if (vote.isPresent()) {
                return new ResponseEntity<>(vote.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Vote poll failed", HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("an unknown error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAuthority('Muzfi_Member')")
    @GetMapping("/remove-vote/{pollOptionId}/user/{userId}")
    public ResponseEntity<?> removeVoteFromPoll(@PathVariable("pollOptionId") String pollOptionId, @PathVariable("userId") String userId) {
        try {
            boolean isLoggedInUser = authService.isLoggedInUser(userId);

            if (!isLoggedInUser) {
                return new ResponseEntity<>("Access denied: You are not eligible to perform this action.", HttpStatus.UNAUTHORIZED);
            }

            Optional<String> voteRemoved = pollService.removeVote(pollOptionId, userId);

            if (voteRemoved.isPresent()) {
                return new ResponseEntity<>(voteRemoved.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Remove vote from poll failed", HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("an unknown error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAuthority('Muzfi_Member')")
    @PutMapping
    public ResponseEntity<?> updatePoll(@RequestBody PollUpdateDto pollUpdateDto) {
        try {
            Optional<PollDetailsDto> pollOptional = pollService.getPollById(pollUpdateDto.getId());

            if (pollOptional.isEmpty()) {
                return new ResponseEntity<>("No existing poll found", HttpStatus.NOT_FOUND);
            }

            boolean isLoggedInUser = authService.isLoggedInUser(pollOptional.get().getAuthorId());

            if (!isLoggedInUser) {
                return new ResponseEntity<>("Access denied: You are not eligible to perform this action.", HttpStatus.UNAUTHORIZED);
            }

            Optional<PostDetailsDto> postUpdated = pollService.updatePoll(pollUpdateDto);

            if (postUpdated.isPresent()) {
                return new ResponseEntity<>(postUpdated.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Update poll failed", HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("an unknown error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAuthority('Muzfi_Member')")
    @GetMapping("/{pollId}/new-option")
    public ResponseEntity<?> addNewPollOption(@PathVariable("pollId") String pollId, @RequestParam("text") String pollOptionText) {
        try {
            Optional<PollDetailsDto> pollOptional = pollService.getPollById(pollId);

            if (pollOptional.isEmpty()) {
                return new ResponseEntity<>("No existing poll found", HttpStatus.NOT_FOUND);
            }

            boolean isLoggedInUser = authService.isLoggedInUser(pollOptional.get().getAuthorId());

            if (!isLoggedInUser) {
                return new ResponseEntity<>("Access denied: You are not eligible to perform this action.", HttpStatus.UNAUTHORIZED);
            }

            Optional<PostDetailsDto> postUpdated = pollService.addPollOption(pollOptionText, pollId);

            if (postUpdated.isPresent()) {
                return new ResponseEntity<>(postUpdated.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Add new poll option failed", HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("an unknown error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAuthority('Muzfi_Member')")
    @GetMapping("/{pollId}/remove-option/{optionId}")
    public ResponseEntity<?> removePollOption(@PathVariable("pollId") String pollId, @PathVariable("optionId") String optionId) {
        try {
            Optional<PollDetailsDto> pollOptional = pollService.getPollById(pollId);

            if (pollOptional.isEmpty()) {
                return new ResponseEntity<>("No existing poll found", HttpStatus.NOT_FOUND);
            }

            boolean isLoggedInUser = authService.isLoggedInUser(pollOptional.get().getAuthorId());

            if (!isLoggedInUser) {
                return new ResponseEntity<>("Access denied: You are not eligible to perform this action.", HttpStatus.UNAUTHORIZED);
            }

            Optional<PostDetailsDto> postUpdated = pollService.removePollOption(optionId, pollId);

            if (postUpdated.isPresent()) {
                return new ResponseEntity<>(postUpdated.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Remove poll option failed", HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("an unknown error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
