package com.example.muzfi.Controller.User;

import com.example.muzfi.Dto.UserDto.AdvanceProfileSettingsUpdateDto;
import com.example.muzfi.Dto.UserDto.PrivacySafetySettingsUpdateDto;
import com.example.muzfi.Dto.UserDto.UserProfileDto;
import com.example.muzfi.Model.User;
import com.example.muzfi.Model.UserSetting;
import com.example.muzfi.Services.AuthService;
import com.example.muzfi.Services.User.UserService;
import com.example.muzfi.Services.User.UserSettingService;
import com.plaid.client.model.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/profile/settings")
@RequiredArgsConstructor
public class UserSettingController {

    private final UserSettingService userSettingService;

    private final AuthService authService;

    private final UserService userService;


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

    // update logged-in  user advance profile settings
    @PreAuthorize("hasAuthority('Muzfi_Member')")
    @PutMapping("/advance-profile-settings/{userId}")
    public ResponseEntity<?> updateLoggedInUserAdvanceProfileSettings(@PathVariable("userId") String userId, @RequestBody AdvanceProfileSettingsUpdateDto settings) {
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
            existingSetting.setIsAllowToFollow(settings.getIsAllowToFollow());
            existingSetting.setIsContentVisible(settings.getIsContentVisible());
            existingSetting.setIsActiveInCommunityVisible(settings.getIsActiveInCommunityVisible());

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

    // update logged-in  user privacy safety settings
    @PreAuthorize("hasAuthority('Muzfi_Member')")
    @PutMapping("/privacy-safety-settings/{userId}")
    public ResponseEntity<?> updateLoggedInUserPrivacySafetySettings(@PathVariable("userId") String userId, @RequestBody PrivacySafetySettingsUpdateDto settings) {
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
            existingSetting.setIsShowsUpInSearchResults(settings.getIsShowsUpInSearchResults());

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

    @PreAuthorize("hasAuthority('Muzfi_Member')")
    @PutMapping("/email/{userId}")
    public ResponseEntity<?> updateLoggedInUserEmail(@PathVariable("userId") String userId, String emailUpdate) {
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
            existingSetting.setEmail(emailUpdate);

            Optional<UserSetting> updatedSetting = userSettingService.updateUserSetting(existingSetting);

            if (updatedSetting.isPresent()) {
                // Update the corresponding fields in the User document
                Optional<User> userOptional = userService.getUserById(userId);

                if (userOptional.isPresent()) {
                    User user = userOptional.get();
                    user.setEmail(emailUpdate);
                    // Update other fields as needed
                    userService.updateUser(user);
                }

                return new ResponseEntity<>(convertToUserProfileDto(updatedSetting.get()), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("User profile setting update failed", HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("an unknown error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private UserProfileDto convertToUserProfileDto(UserSetting userSetting) {
        UserProfileDto userProfileDto = new UserProfileDto();
        userProfileDto.setEmail(userSetting.getEmail());
        userProfileDto.setGender(userSetting.getGender());
        userProfileDto.setCountry(userSetting.getCountry());
        userProfileDto.setState(userSetting.getState());
        userProfileDto.setCity(userSetting.getCity());
        return userProfileDto;
    }
}
