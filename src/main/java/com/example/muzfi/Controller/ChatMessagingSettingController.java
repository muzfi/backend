package com.example.muzfi.Controller;

import com.example.muzfi.Model.ChatMessagingSetting;
import com.example.muzfi.Services.AuthService;
import com.example.muzfi.Services.ChatMessagingSettingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/chat/setting")
@RequiredArgsConstructor
public class ChatMessagingSettingController {
    private final ChatMessagingSettingService chatMessagingSettingService;
    private final AuthService authService;

    @PreAuthorize("hasAuthority('Muzfi_Member')")
    @PostMapping
    public ResponseEntity<?> createChatMessagingSettings(@RequestBody ChatMessagingSetting settings) {
        try {
            ChatMessagingSetting createdSettings = chatMessagingSettingService.createChatMessagingSettings(settings);
            return new ResponseEntity<>(createdSettings, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>("An unknown error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAuthority('Muzfi_Member')")
    @GetMapping("/{userId}")
    public ResponseEntity<?> getChatMessagingSettingsByUserId(@PathVariable("userId") String userId) {
        try {
            Optional<ChatMessagingSetting> chatMessagingSettings = chatMessagingSettingService.getChatMessagingSettingsByUserId(userId);

            if (chatMessagingSettings.isPresent()) {
                return new ResponseEntity<>(chatMessagingSettings.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Chat messaging settings not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("An unknown error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAuthority('Muzfi_Member')")
    @PutMapping
    public ResponseEntity<?> updateChatMessagingSettings(@RequestBody ChatMessagingSetting settings) {
        try {
            // Validate the input, ensuring that necessary fields are provided
            if (settings == null || settings.getUserId() == null) {
                return new ResponseEntity<>("Invalid input. Please provide userId and chat messaging settings.", HttpStatus.BAD_REQUEST);
            }

            // Check if the logged-in user is the owner of the chat messaging settings
            boolean isLoggedInUser = authService.isLoggedInUser(settings.getUserId());
            if (!isLoggedInUser) {
                return new ResponseEntity<>("Access denied: You cannot update chat messaging settings for another user.", HttpStatus.UNAUTHORIZED);
            }

            // Update the user chat messaging settings
            Optional<ChatMessagingSetting> updatedSettings = chatMessagingSettingService.updateChatMessagingSettings(settings);

            if (updatedSettings.isPresent()) {
                return new ResponseEntity<>(updatedSettings.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Update chat messaging settings failed.", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("An unknown error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
