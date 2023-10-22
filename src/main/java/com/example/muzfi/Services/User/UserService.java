package com.example.muzfi.Services.User;

import com.example.muzfi.Dto.PostDto.PostAuthorDto;
import com.example.muzfi.Dto.UserDto.UserBasicDto;
import com.example.muzfi.Enums.UserRole;
import com.example.muzfi.Model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<List<User>> getAllUsers();

    User createUser(User user);

    Optional<User> getUserByOktaId(String oktaId);

    Optional<User> getUserById(String userId);

    Optional<List<UserBasicDto>> getUsersByIds(List<String> userIds);

    Optional<List<UserBasicDto>> getBlockedUsersByUser(String userId);

    Optional<String> getOktaIdByUserId(String userId);

    User updateUserRole(String userId, List<UserRole> userRoles);

    Optional<String> followUser(String loggedInUserId, String followingUserId);

    Optional<String> unFollowUser(String loggedInUserId, String unFollowingUserId);

    Optional<String> blockUser(String loggedInUserId, String blockUserId);

    Optional<String> unBlockUser(String loggedInUserId, String unBlockUserId);

    Optional<PostAuthorDto> getPostAuthor(String authorId);
}
