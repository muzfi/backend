package com.example.muzfi.Controller.User;

import com.example.muzfi.Model.UserSetting;
import com.example.muzfi.Services.AuthService;
import com.example.muzfi.Services.User.UserSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/profile/settings")
public class UserSettingController {

    private final UserSettingService userSettingService;

    private final AuthService authService;

    @Autowired
    public UserSettingController(UserSettingService userSettingService, AuthService authService) {
        this.userSettingService = userSettingService;
        this.authService = authService;
    }

    // update logged-in  user display language
    @PreAuthorize("hasAuthority('Muzfi_Member')")
    @PutMapping("/display-lang/{userId}")
    public ResponseEntity<?> updateLoggedInUserDisplayLanguage(@PathVariable("userId") String userId, @RequestParam(name = "display_lang") String lang) {
        try {
            boolean isLoggedInUser = authService.isLoggedInUser(userId);

            if (!isLoggedInUser) {
                return new ResponseEntity<>("Access denied: You cannot update this user profile.", HttpStatus.UNAUTHORIZED);
            }

            Optional<UserSetting> settingOptional = userSettingService.getUserSettingByUserId(userId);

            if (settingOptional.isEmpty()) {
                return new ResponseEntity<>("User profile setting not found", HttpStatus.NOT_FOUND);
            }

            UserSetting existingSetting = settingOptional.get();
            existingSetting.setDisplayLang(lang);

            Optional<UserSetting> updatedSetting = userSettingService.updateUserSetting(existingSetting);

            if (updatedSetting.isPresent()) {
                return new ResponseEntity<>(updatedSetting, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("User profile setting update failed", HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("an unknown error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // update logged-in  user display language
    @PreAuthorize("hasAuthority('Muzfi_Member')")
    @PutMapping("/content-lang/{userId}")
    public ResponseEntity<?> updateLoggedInUserContentLanguage(@PathVariable("userId") String userId, @RequestParam(name = "content_lang") String lang) {
        try {
            boolean isLoggedInUser = authService.isLoggedInUser(userId);

            if (!isLoggedInUser) {
                return new ResponseEntity<>("Access denied: You cannot update this user profile.", HttpStatus.UNAUTHORIZED);
            }

            Optional<UserSetting> settingOptional = userSettingService.getUserSettingByUserId(userId);

            if (settingOptional.isEmpty()) {
                return new ResponseEntity<>("User profile setting not found", HttpStatus.NOT_FOUND);
            }

            UserSetting existingSetting = settingOptional.get();
            existingSetting.setContentLang(lang);

            Optional<UserSetting> updatedSetting = userSettingService.updateUserSetting(existingSetting);

            if (updatedSetting.isPresent()) {
                return new ResponseEntity<>(updatedSetting, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("User profile setting update failed", HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("an unknown error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
