package com.example.muzfi.Services.User;

import com.example.muzfi.Dto.UserDto.UserProfileDto;
import com.example.muzfi.Dto.UserDto.UserProfileUpdateDto;
import com.example.muzfi.Model.User;
import com.example.muzfi.Repository.UserRepository;
import com.example.muzfi.Services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

@Component
public class UserProfileServiceImpl implements UserProfileService {

    private final UserRepository userRepository;

    private final AuthService authService;

    @Autowired
    public UserProfileServiceImpl(UserRepository userRepository, AuthService authService) {
        this.userRepository = userRepository;
        this.authService = authService;
    }

    @Override
    public Optional<UserProfileDto> getUserProfileByUserId(String userId) {
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            boolean isFollowed = false;

            boolean isBlocked = false;
            User loggedInUser;

            try {
                loggedInUser = authService.getLoggedInUser();
            } catch (Exception ex) {
                loggedInUser = null;
            }

            if (loggedInUser != null) {

                // if logged in got blockedBy selected user profile owner, does not return the profile
                for (String blockedById : loggedInUser.getBlockedByUserIds()) {
                    if (blockedById.equals(userId)) return Optional.empty();
                }

                //isFollowed
                Set<String> followersList = user.getFollowersUserIds();
                if (!followersList.isEmpty()) {
                    for (String followerId : followersList) {
                        if (followerId.equals(loggedInUser.getId())) isFollowed = true;
                    }
                }


                //isBlocked
                Set<String> blockedByList = user.getBlockedByUserIds();
                if (!blockedByList.isEmpty()) {
                    for (String blockedId : blockedByList) {
                        if (blockedId.equals(loggedInUser.getId())) isBlocked = true;
                    }
                }
            }

            //TODO: user post count
            int noOfPosts = 0;

            //TODO: user gear count
            int noOfGears = 0;

            //TODO: user sales count
            int noOfSales = 0;

            //TODO: calculate seller ratings
            double sellerRatings = 0;

            //TODO: calculate buyer ratings
            double buyerRatings = 0;


            UserProfileDto userProfileDto = new UserProfileDto();

            userProfileDto.setUserName(user.getUserName());
            userProfileDto.setEmail(user.getEmail());
            userProfileDto.setFirstName(user.getFirstName());
            userProfileDto.setLastName(user.getLastName());
            userProfileDto.setDescription(user.getDescription());
            userProfileDto.setLocation(user.getLocation());
            userProfileDto.setProfileUrl(user.getProfilePicUri());
            userProfileDto.setNoOfPosts(noOfPosts);
            userProfileDto.setNoOfGears(noOfGears);
            userProfileDto.setNoOfSales(noOfSales);
            userProfileDto.setMuzPoints(user.getMuzPoints());
            userProfileDto.setBirthDate(user.getBirthDate());
            userProfileDto.setSellerRatings(sellerRatings);
            userProfileDto.setBuyerRatings(buyerRatings);
            userProfileDto.setIsFollowed(isFollowed);
            userProfileDto.setIsBlocked(isBlocked);
            userProfileDto.setCreatedDateTime(user.getCreatedDateTime());
            userProfileDto.setLastUpdatedDateTime(user.getLastUpdatedDateTime());

            return Optional.of(userProfileDto);

        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<UserProfileDto> updateUserProfilePic(String userid, String picUrl) {
        Optional<User> userOptional = userRepository.findById(userid);

        if (userOptional.isPresent()) {
            User existingUser = userOptional.get();
            existingUser.setProfilePicUri(picUrl);
            existingUser.setLastUpdatedDateTime(LocalDateTime.now());

            userRepository.save(existingUser);

            Optional<UserProfileDto> response = getUserProfileByUserId(userid);

            return response;

        } else {
            return Optional.empty();
        }
    }


    @Override
    public Optional<UserProfileDto> updateUserProfile(String userId, UserProfileUpdateDto user) {
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            User existingUser = userOptional.get();

            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            existingUser.setLocation(user.getLocation());
            existingUser.setBirthDate(user.getBirthDate());
            existingUser.setDescription(user.getDescription());
            existingUser.setProfilePicUri(user.getProfilePicUri());

            userRepository.save(existingUser);

            Optional<UserProfileDto> response = getUserProfileByUserId(userId);

            return response;
        } else {
            return Optional.empty();
        }
    }
}
