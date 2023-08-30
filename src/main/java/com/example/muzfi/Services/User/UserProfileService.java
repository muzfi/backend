package com.example.muzfi.Services.User;

import com.example.muzfi.Dto.UserDto.UserProfileDto;
import com.example.muzfi.Model.User;

import java.util.Optional;

public interface UserProfileService {
    Optional<UserProfileDto> getUserProfileByUserId(String id);

    Optional<UserProfileDto> updateUserProfile(User user);
}
