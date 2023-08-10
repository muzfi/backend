package com.example.muzfi.Controller.User;

import com.example.muzfi.Dto.UserProfileDto;
import com.example.muzfi.Services.User.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/profile")
public class UserProfileController {
    private final UserProfileService userProfileService;

    @Autowired
    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @PreAuthorize("hasAuthority('Muzfi_Member')")
    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserProfileById(@PathVariable("userId") String userId) {
        try {
            Optional<UserProfileDto> userProfile = userProfileService.getUserProfileByUserId(userId);

            if (userProfile.isPresent()) {
                return new ResponseEntity<>(userProfile, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No User Profile Available", HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("an unknown error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
