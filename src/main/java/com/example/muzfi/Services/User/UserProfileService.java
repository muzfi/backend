package com.example.muzfi.Services.User;

import com.example.muzfi.Dto.UserDto.UserProfileDto;
import com.example.muzfi.Model.User;

import java.util.Optional;

public interface UserProfileService {
    static User Update(User user) {
        return null;
    }

    Optional<UserProfileDto> getUserProfileByUserId(Long userId);

    Optional<UserProfileDto> getUserProfileByUserId(String id);

    Optional<UserProfileDto> updateUserProfile(User user);
}
