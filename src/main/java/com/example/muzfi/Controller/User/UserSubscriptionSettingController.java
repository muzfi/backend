package com.example.muzfi.Controller.User;

import com.example.muzfi.Model.UserSubscriptionSetting;
import com.example.muzfi.Services.AuthService;
import com.example.muzfi.Services.User.UserSubscriptionSettingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/userSubscriptionSetting")
@RequiredArgsConstructor
public class UserSubscriptionSettingController {
    private final UserSubscriptionSettingService userSubscriptionSettingService;
    private final AuthService authService;

    @PreAuthorize("hasAuthority('Muzfi_Member')")
    @PostMapping
    public ResponseEntity<?> createUserSubscriptionSetting(@RequestBody UserSubscriptionSetting subscriptionSetting) {
        try {
            UserSubscriptionSetting createdSetting = userSubscriptionSettingService.createUserSubscriptionSetting(subscriptionSetting);
            return new ResponseEntity<>(createdSetting, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>("Failed to create subscription setting. " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAuthority('Muzfi_Member')")
    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserSubscriptionSettingByUserId(@PathVariable("userId") String userId) {
        try {
            Optional<UserSubscriptionSetting> subscriptionSetting = userSubscriptionSettingService.getUserSubscriptionSettingByUserId(userId);

            if (subscriptionSetting.isPresent()) {
                return new ResponseEntity<>(subscriptionSetting.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Subscription setting not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("An unknown error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAuthority('Muzfi_Member')")
    @PutMapping
    public ResponseEntity<?> updateUserSubscriptionSetting(@RequestBody UserSubscriptionSetting subscriptionSetting) {
        try {
            // Validate the input, ensuring that necessary fields are provided
            if (subscriptionSetting == null || subscriptionSetting.getUserId() == null || subscriptionSetting.getSubscriptionType() == null) {
                return new ResponseEntity<>("Invalid input. Please provide userId and subscriptionType.", HttpStatus.BAD_REQUEST);
            }

            // Check if the logged-in user is the owner of the subscription
            boolean isLoggedInUser = authService.isLoggedInUser(subscriptionSetting.getUserId());
            if (!isLoggedInUser) {
                return new ResponseEntity<>("Access denied: You cannot update subscription setting for another user.", HttpStatus.UNAUTHORIZED);
            }

            // Update the user subscription setting
            Optional<UserSubscriptionSetting> updatedSetting = userSubscriptionSettingService.updateUserSubscriptionSetting(subscriptionSetting);

            if (updatedSetting.isPresent()) {
                return new ResponseEntity<>(updatedSetting.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Update subscription setting failed.", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("An unknown error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
