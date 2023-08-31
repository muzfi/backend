package com.example.muzfi.Services.User;

import com.example.muzfi.Dto.PostDto.PostAuthorDto;
import com.example.muzfi.Dto.UserDto.UserBasicDto;
import com.example.muzfi.Enums.UserRole;
import com.example.muzfi.Model.User;
import com.example.muzfi.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<List<User>> getAllUsers() {
        return Optional.of(userRepository.findAll());
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserByOktaId(String oktaId) {
        Optional<User> optionalUser = userRepository.findUserByOktaId(oktaId);

        return optionalUser;
    }

    @Override
    public Optional<User> getUserById(String userId) {
        Optional<User> optionalUser = userRepository.findById(userId);

        return optionalUser;
    }

    @Override
    public Optional<List<UserBasicDto>> getUsersByIds(List<String> userIds) {
        List<User> userList = userRepository.findAllById(userIds);

        if (userList.isEmpty()) {
            return Optional.empty();
        }

        List<UserBasicDto> dtoList = new ArrayList<>();

        for (User user : userList) {
            UserBasicDto userDto = new UserBasicDto();
            userDto.setUserName(user.getUserName());
            userDto.setFirstName(user.getFirstName());
            userDto.setLastName(user.getLastName());
            userDto.setDisplayName(user.getDisplayName());
            userDto.setId(user.getId());
            userDto.setProfileUrl(user.getProfilePicUri());

            dtoList.add(userDto);
        }

        return Optional.of(dtoList);
    }

    @Override
    public Optional<List<UserBasicDto>> getBlockedUsersByUser(String userId) {

        Optional<User> loggedInUserOpt = userRepository.findById(userId);

        if (loggedInUserOpt.isEmpty()) {
            return Optional.empty();
        }

        User loggedInUser = loggedInUserOpt.get();
        List<User> userList = userRepository.findAllById(loggedInUser.getBlockedUserIds());

        if (userList.isEmpty()) {
            return Optional.empty();
        }

        List<UserBasicDto> dtoList = new ArrayList<>();

        for (User user : userList) {
            UserBasicDto userDto = new UserBasicDto();
            userDto.setUserName(user.getUserName());
            userDto.setFirstName(user.getFirstName());
            userDto.setLastName(user.getLastName());
            userDto.setDisplayName(user.getDisplayName());
            userDto.setId(user.getId());
            userDto.setProfileUrl(user.getProfilePicUri());

            dtoList.add(userDto);
        }

        return Optional.of(dtoList);
    }

    @Override
    public Optional<String> getOktaIdByUserId(String userId) {
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()) {
            String userOktaId = optionalUser.get().getOktaId();
            return Optional.of(userOktaId);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public User updateUserRole(String userId, List<UserRole> userRoles) {
        User existingUser = userRepository.findById(userId).orElse(null);
        if (existingUser != null) {
            existingUser.setRole(userRoles);
            return userRepository.save(existingUser);
        }
        return null;
    }

    @Override
    public Optional<String> followUser(String loggedInUserId, String followingUserId) {
        Optional<User> loggedInUserOptional = userRepository.findById(loggedInUserId);
        Optional<User> followingUserOptional = userRepository.findById(followingUserId);

        if (loggedInUserOptional.isPresent() && followingUserOptional.isPresent()) {
            User loggedInUser = loggedInUserOptional.get();
            User followingUser = followingUserOptional.get();

            Set<String> loggedInUserFollowingList = loggedInUser.getFollowingsUserIds();
            if (loggedInUserFollowingList == null) loggedInUserFollowingList = new HashSet<>();
            loggedInUserFollowingList.add(followingUserId);
            loggedInUser.setFollowingsUserIds(loggedInUserFollowingList);

            Set<String> followingUserFollowersList = followingUser.getFollowersUserIds();
            if (followingUserFollowersList == null) followingUserFollowersList = new HashSet<>();
            followingUserFollowersList.add(loggedInUserId);
            followingUser.setFollowersUserIds(followingUserFollowersList);

            userRepository.save(loggedInUser);
            userRepository.save(followingUser);

            return Optional.of(followingUser.getFirstName() + " is followed");
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<String> unFollowUser(String loggedInUserId, String unFollowingUserId) {
        Optional<User> loggedInUserOptional = userRepository.findById(loggedInUserId);
        Optional<User> unFollowingUserOptional = userRepository.findById(unFollowingUserId);

        if (loggedInUserOptional.isPresent() && unFollowingUserOptional.isPresent()) {
            User loggedInUser = loggedInUserOptional.get();
            User unFollowingUser = unFollowingUserOptional.get();

            Set<String> loggedInUserFollowingList = loggedInUser.getFollowingsUserIds();
            loggedInUserFollowingList.remove(unFollowingUserId);
            loggedInUser.setFollowingsUserIds(loggedInUserFollowingList);

            Set<String> unFollowingUserFollowersList = unFollowingUser.getFollowersUserIds();
            unFollowingUserFollowersList.remove(loggedInUserId);
            unFollowingUser.setFollowersUserIds(unFollowingUserFollowersList);

            userRepository.save(loggedInUser);
            userRepository.save(unFollowingUser);

            return Optional.of(unFollowingUser.getFirstName() + " is unfollowed");
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<String> blockUser(String loggedInUserId, String blockUserId) {
        Optional<User> loggedInUserOptional = userRepository.findById(loggedInUserId);
        Optional<User> blockUserOptional = userRepository.findById(blockUserId);

        if (loggedInUserOptional.isPresent() && blockUserOptional.isPresent()) {
            User loggedInUser = loggedInUserOptional.get();
            User blockedUser = blockUserOptional.get();

            Set<String> loggedInUserBlockedList = loggedInUser.getBlockedUserIds();
            if (loggedInUserBlockedList == null) loggedInUserBlockedList = new HashSet<>();
            loggedInUserBlockedList.add(blockUserId);
            loggedInUser.setBlockedUserIds(loggedInUserBlockedList);

            Set<String> blockedUserBlockedByList = blockedUser.getBlockedByUserIds();
            if (blockedUserBlockedByList == null) blockedUserBlockedByList = new HashSet<>();
            blockedUserBlockedByList.add(loggedInUserId);
            blockedUser.setBlockedByUserIds(blockedUserBlockedByList);

            userRepository.save(loggedInUser);
            userRepository.save(blockedUser);

            return Optional.of(blockedUser.getFirstName() + " is blocked");
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<String> unBlockUser(String loggedInUserId, String unBlockUserId) {
        Optional<User> loggedInUserOptional = userRepository.findById(loggedInUserId);
        Optional<User> unBlockUserOptional = userRepository.findById(unBlockUserId);

        if (loggedInUserOptional.isPresent() && unBlockUserOptional.isPresent()) {
            User loggedInUser = loggedInUserOptional.get();
            User unBlockedUser = unBlockUserOptional.get();

            Set<String> loggedInUserBlockedList = loggedInUser.getBlockedUserIds();
            loggedInUserBlockedList.remove(unBlockUserId);
            loggedInUser.setBlockedUserIds(loggedInUserBlockedList);

            Set<String> unBlockedUserBlockedByList = unBlockedUser.getBlockedByUserIds();
            unBlockedUserBlockedByList.remove(loggedInUserId);
            unBlockedUser.setBlockedByUserIds(unBlockedUserBlockedByList);

            userRepository.save(loggedInUser);
            userRepository.save(unBlockedUser);

            return Optional.of(unBlockedUser.getFirstName() + " is unblocked");
        } else {
            return Optional.empty();
        }
    }

    //Retrieve post author data by author id
    @Override
    public Optional<PostAuthorDto> getPostAuthor(String authorId) {

        Optional<User> userOptional = userRepository.findById(authorId);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            PostAuthorDto authorDto = new PostAuthorDto();

            authorDto.setId(user.getId());
            authorDto.setUsername(user.getUserName());
            authorDto.setAvatar(user.getProfilePicUri());
            authorDto.setName(user.getFirstName() + " " + user.getLastName());
            authorDto.setLocation(user.getLocation());
            authorDto.setMuzPoints(user.getMuzPoints());

            //TODO: Set ratings

            //TODO: Set review count

            //TODO: Set seller ratings

            return Optional.of(authorDto);

        } else {
            return Optional.empty();
        }
    }
}
