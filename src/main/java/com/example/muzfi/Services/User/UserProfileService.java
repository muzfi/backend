package com.example.muzfi.Services.User;

import com.example.muzfi.Dto.UserDto.UserProfileDto;
import com.example.muzfi.Dto.UserDto.UserProfileUpdateDto;

import java.util.Optional;

public interface UserProfileService {
    Optional<UserProfileDto> getUserProfileByUserId(String id);

    Optional<UserProfileDto> updateUserProfilePic(String userid, String picUrl);

    Optional<UserProfileDto> updateUserProfile(String userId, UserProfileUpdateDto user);
}
