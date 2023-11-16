package com.example.muzfi.Controller.User;

import com.example.muzfi.Dto.UserDto.SignupConfirmationRequest;
import com.example.muzfi.Dto.UserDto.UserBasicDto;
import com.example.muzfi.Model.User;
import com.example.muzfi.Services.AuthService;
import com.example.muzfi.Services.EmailConfirmationService.EmailConfirmationService;
import com.example.muzfi.Services.EmailConfirmationService.EmailNotification.EmailNotificationService;
import com.example.muzfi.Services.User.UserService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    private final AuthService authService;
    private final EmailConfirmationService emailConfirmationService;

    private final EmailNotificationService emailNotificationService;

    @Autowired
    public UserController(UserService userService, AuthService authService, EmailConfirmationService emailConfirmationService, EmailNotificationService emailNotificationService) {
        this.userService = userService;
        this.authService = authService;
        this.emailConfirmationService = emailConfirmationService;
        this.emailNotificationService = emailNotificationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> sendConfirmationEmail(@RequestBody SignupConfirmationRequest signupConfirmationRequest) {
        try {
            // Invoke the email service to send the confirmation email
            emailConfirmationService.sendSignUpConfirmationEmail(signupConfirmationRequest.getEmail(), signupConfirmationRequest.getConfirmationToken());
            return new ResponseEntity<>("Confirmation email sent successfully", HttpStatus.OK);
        } catch (MessagingException e) {
            return new ResponseEntity<>("Error sending confirmation email: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        try {
            Optional<List<User>> users = userService.getAllUsers();

            if (users.isPresent()) {
                return new ResponseEntity<>(users, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No Users Available", HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("an unknown error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //TODO: Remove later: For testing purposes
    @GetMapping("/okta/{userId}")
    public ResponseEntity<?> getAUserByOktaId(@PathVariable("userId") String oktaUserId) {
        try {
            Optional<User> user = userService.getUserByOktaId(oktaUserId);

            if (user.isPresent()) {
                return new ResponseEntity<>(user, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No User Available", HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("an unknown error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/by-ids")
    public ResponseEntity<?> getUsersByIdList(@RequestBody List<String> ids) {
        try {
            Optional<List<UserBasicDto>> users = userService.getUsersByIds(ids);

            if (users.isPresent()) {
                return new ResponseEntity<>(users, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No User Available", HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("an unknown error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAuthority('Muzfi_Member')")
    @GetMapping("/{loggedInUserId}/follow/{followingUserId}")
    public ResponseEntity<?> followUser(@PathVariable("followingUserId") String followingUserId, @PathVariable(name = "loggedInUserId") String loggedInUserId) {
        try {
            boolean isLoggedInUser = authService.isLoggedInUser(loggedInUserId);

            if (!isLoggedInUser) {
                return new ResponseEntity<>("Access denied: You are not eligible to perform this action.", HttpStatus.UNAUTHORIZED);
            }

            Optional<String> follow = userService.followUser(loggedInUserId, followingUserId);

            if (follow.isPresent()) {
                return new ResponseEntity<>(follow.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("User following action failed", HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("an unknown error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAuthority('Muzfi_Member')")
    @GetMapping("/{loggedInUserId}/unfollow/{unFollowingUserId}")
    public ResponseEntity<?> unfollowUser(@PathVariable("unFollowingUserId") String unFollowingUserId, @PathVariable(name = "loggedInUserId") String loggedInUserId) {
        try {
            boolean isLoggedInUser = authService.isLoggedInUser(loggedInUserId);

            if (!isLoggedInUser) {
                return new ResponseEntity<>("Access denied: You are not eligible to perform this action.", HttpStatus.UNAUTHORIZED);
            }

            Optional<String> unFollow = userService.unFollowUser(loggedInUserId, unFollowingUserId);

            if (unFollow.isPresent()) {
                return new ResponseEntity<>(unFollow.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("User unfollowing action failed", HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("an unknown error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAuthority('Muzfi_Member')")
    @GetMapping("/{loggedInUserId}/block/{blockUserId}")
    public ResponseEntity<?> blockUser(@PathVariable("blockUserId") String blockUserId, @PathVariable(name = "loggedInUserId") String loggedInUserId) {
        try {
            boolean isLoggedInUser = authService.isLoggedInUser(loggedInUserId);

            if (!isLoggedInUser) {
                return new ResponseEntity<>("Access denied: You are not eligible to perform this action.", HttpStatus.UNAUTHORIZED);
            }

            Optional<String> block = userService.blockUser(loggedInUserId, blockUserId);

            if (block.isPresent()) {
                return new ResponseEntity<>(block.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("User blocking action failed", HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("an unknown error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAuthority('Muzfi_Member')")
    @GetMapping("/{loggedInUserId}/unblock/{unBlockUserId}")
    public ResponseEntity<?> unBlockUser(@PathVariable("unBlockUserId") String unBlockUserId, @PathVariable(name = "loggedInUserId") String loggedInUserId) {
        try {
            boolean isLoggedInUser = authService.isLoggedInUser(loggedInUserId);

            if (!isLoggedInUser) {
                return new ResponseEntity<>("Access denied: You are not eligible to perform this action.", HttpStatus.UNAUTHORIZED);
            }

            Optional<String> unblock = userService.unBlockUser(loggedInUserId, unBlockUserId);

            if (unblock.isPresent()) {
                return new ResponseEntity<>(unblock.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("User unblocking action failed", HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("an unknown error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/invite")
    @PreAuthorize("hasAuthority('Muzfi_Member')")
    public ResponseEntity<String> inviteUser(@RequestBody User inviteUserRequest, @PathVariable("loggedInUserId") String loggedInUserId) {
        try {
            boolean isLoggedInUser = authService.isLoggedInUser(loggedInUserId);

            if (!isLoggedInUser) {
                return new ResponseEntity<>("Access denied: You are not eligible to perform this action.", HttpStatus.UNAUTHORIZED);
            }

            // Implement your logic to send an invitation to the specified email address
            emailNotificationService.sendInvitationEmail(inviteUserRequest.getEmail());

            return new ResponseEntity<>("Invitation sent successfully", HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>("An unknown error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
