package com.example.muzfi.Controller;

import com.example.muzfi.Model.NotificationSettings;
import com.example.muzfi.Services.AuthService;
import com.example.muzfi.Services.NotificationSettingsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/notification/settings")
@RequiredArgsConstructor
public class NotificationSettingsController {
    private final NotificationSettingsService notificationSettingsService;
    private final AuthService authService;

    @PreAuthorize("hasAuthority('Muzfi_Member')")
    @PostMapping
    public ResponseEntity<?> createNotificationSettings(@RequestBody NotificationSettings settings) {
        try {
            NotificationSettings createdSettings = notificationSettingsService.createNotificationSettings(settings);
            return new ResponseEntity<>(createdSettings, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>("An unknown error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAuthority('Muzfi_Member')")
    @GetMapping("/{userId}")
    public ResponseEntity<?> getNotificationSettingsByUserId(@PathVariable("userId") String userId) {
        try {
            Optional<NotificationSettings> notificationSettings = notificationSettingsService.getNotificationSettingsByUserId(userId);

            if (notificationSettings.isPresent()) {
                return new ResponseEntity<>(notificationSettings.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Notification settings not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("An unknown error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAuthority('Muzfi_Member')")
    @PutMapping
    public ResponseEntity<?> updateNotificationSettings(@RequestBody NotificationSettings settings) {
        try {
            // Validate the input, ensuring that necessary fields are provided
            if (settings == null || settings.getUserId() == null) {
                return new ResponseEntity<>("Invalid input. Please provide userId and notification settings.", HttpStatus.BAD_REQUEST);
            }

            // Check if the logged-in user is the owner of the notification settings
            boolean isLoggedInUser = authService.isLoggedInUser(settings.getUserId());
            if (!isLoggedInUser) {
                return new ResponseEntity<>("Access denied: You cannot update notification settings for another user.", HttpStatus.UNAUTHORIZED);
            }

            // Update the user notification settings
            Optional<NotificationSettings> updatedSettings = notificationSettingsService.updateNotificationSettings(settings);

            if (updatedSettings.isPresent()) {
                return new ResponseEntity<>(updatedSettings.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Update notification settings failed.", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("An unknown error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
