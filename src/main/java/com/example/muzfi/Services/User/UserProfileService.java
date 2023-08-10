package com.example.muzfi.Services.User;

import com.example.muzfi.Dto.UserProfileDto;

import java.util.Optional;

public interface UserProfileService {
    Optional<UserProfileDto> getUserProfileByUserId(String id);
}
