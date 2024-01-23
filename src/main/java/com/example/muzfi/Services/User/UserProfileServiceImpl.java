package com.example.muzfi.Services.User;

import com.example.muzfi.Dto.UserDto.UserProfileDto;
import com.example.muzfi.Model.User;
import com.example.muzfi.Repository.UserRepository;
import com.example.muzfi.Services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    private UserRepository userRepository;

    private  AuthService authService;

    @Autowired
    public UserProfileServiceImpl(UserRepository userRepository, AuthService authService) {
        this.userRepository = userRepository;
        this.authService = authService;
    }

    public UserProfileServiceImpl() {

    }

    @Override
    public Optional<UserProfileDto> getUserProfileByUserId(Long userId) {
        Optional<User> userOptional = userRepository.findById(String.valueOf(userId));

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
                if (loggedInUser.getBlockedByUserIds() != null) {
                    for (String blockedById : loggedInUser.getBlockedByUserIds()) {
                        if (blockedById.equals(userId)) return Optional.empty();
                    }
                }

                //isFollowed
                Set<String> followersList = user.getFollowersUserIds();
                if (followersList != null && !followersList.isEmpty()) {
                    for (String followerId : followersList) {
                        if (followerId.equals(loggedInUser.getId())) isFollowed = true;
                    }
                }


                //isBlocked
                Set<String> blockedByList = user.getBlockedByUserIds();
                if (blockedByList != null && !blockedByList.isEmpty()) {
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
            userProfileDto.setDisplayName(user.getDisplayName());
            userProfileDto.setGender(user.getGender());
            userProfileDto.setDescription(user.getDescription());
            userProfileDto.setLocation(user.getLocation());
            userProfileDto.setCountry(user.getCountry());
            userProfileDto.setState(user.getState());
            userProfileDto.setCity(user.getCity());
            userProfileDto.setProfileUrl(user.getProfilePicUri());
            userProfileDto.setBannerImageUrl(user.getBannerImageUri());
            userProfileDto.setSocialLinks(user.getSocialLinks());
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
    public Optional<UserProfileDto> getUserProfileByUserId(String id) {
        return Optional.empty();
    }

    @Override
    public Optional<UserProfileDto> updateUserProfile(User user) {
        Optional<User> userOptional = userRepository.findById(user.getId());

        if (userOptional.isPresent()) {
            User existingUser = userOptional.get();

            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            existingUser.setDisplayName(user.getDisplayName());
            existingUser.setBirthDate(user.getBirthDate());
            existingUser.setDescription(user.getDescription());
            existingUser.setLocation(user.getLocation());
            existingUser.setCity(user.getCity());
            existingUser.setCountry(user.getCountry());
            existingUser.setState(user.getState());
            existingUser.setProfilePicUri(user.getProfilePicUri());
            existingUser.setBannerImageUri(user.getBannerImageUri());
            existingUser.setSocialLinks(user.getSocialLinks());
            existingUser.setGender(user.getGender());
            existingUser.setLastUpdatedDateTime(LocalDateTime.now());

            userRepository.save(existingUser);

            Optional<UserProfileDto> response = getUserProfileByUserId(user.getId());

            return response;
        } else {
            return Optional.empty();
        }
    }
}
