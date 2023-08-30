package com.example.muzfi.Controller.User;

import com.example.muzfi.Dto.UserDto.UserProfileDto;
import com.example.muzfi.Dto.UserDto.UserProfileUpdateDto;
import com.example.muzfi.Enums.UserGender;
import com.example.muzfi.Services.AuthService;
import com.example.muzfi.Services.User.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/profile")
public class UserProfileController {
    private final UserProfileService userProfileService;

    private final AuthService authService;

    @Autowired
    public UserProfileController(UserProfileService userProfileService, AuthService authService) {
        this.userProfileService = userProfileService;
        this.authService = authService;
    }

    // get logged-in or other user profile by id
    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserProfileById(@PathVariable("userId") String userId) {
        try {

            Optional<UserProfileDto> userProfile = userProfileService.getUserProfileByUserId(userId);

            if (userProfile.isPresent()) {
                return new ResponseEntity<>(userProfile, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No User Profile Available", HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("an unknown error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // update logged in  user profile pic uri
    @PreAuthorize("hasAuthority('Muzfi_Member')")
    @PutMapping("/my/user-pic/{userId}")
    public ResponseEntity<?> updateLoggedInUserProfilePic(@PathVariable("userId") String userId, @RequestParam(name = "profilePic") String profilePicUri) {
        try {
            boolean isLoggedInUser = authService.isLoggedInUser(userId);

            if (!isLoggedInUser) {
                return new ResponseEntity<>("Access denied: You cannot update this user profile.", HttpStatus.UNAUTHORIZED);
            }

            Optional<UserProfileDto> updatedUserProfile = userProfileService.updateUserProfilePic(userId, profilePicUri);

            if (updatedUserProfile.isPresent()) {
                return new ResponseEntity<>(updatedUserProfile, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("User profile pic update failed", HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("an unknown error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // update logged in user profile details
    @PreAuthorize("hasAuthority('Muzfi_Member')")
    @PutMapping("/my/{userId}")
    public ResponseEntity<?> updateLoggedInUserProfile(@PathVariable("userId") String userId, @RequestBody UserProfileUpdateDto updatedDetails) {
        try {
            boolean isLoggedInUser = authService.isLoggedInUser(userId);

            if (!isLoggedInUser) {
                return new ResponseEntity<>("Access denied: You cannot update this user profile.", HttpStatus.UNAUTHORIZED);
            }

            Optional<UserProfileDto> updatedUserProfile = userProfileService.updateUserProfile(userId, updatedDetails);

            if (updatedUserProfile.isPresent()) {
                return new ResponseEntity<>(updatedUserProfile, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("User profile update failed", HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("an unknown error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // update logged in  user gender
    @PreAuthorize("hasAuthority('Muzfi_Member')")
    @PutMapping("/my/gender/{userId}")
    public ResponseEntity<?> updateLoggedInUserGender(@PathVariable("userId") String userId, @RequestParam(name = "gender") UserGender gender) {
        try {
            boolean isLoggedInUser = authService.isLoggedInUser(userId);

            if (!isLoggedInUser) {
                return new ResponseEntity<>("Access denied: You cannot update this user profile.", HttpStatus.UNAUTHORIZED);
            }

            Optional<UserProfileDto> updatedUserProfile = userProfileService.updateUserGender(userId, gender);

            if (updatedUserProfile.isPresent()) {
                return new ResponseEntity<>(updatedUserProfile, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("User profile update failed", HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("an unknown error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
