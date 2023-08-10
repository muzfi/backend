package com.example.muzfi.Services.User;

import com.example.muzfi.Dto.UserProfileDto;
import com.example.muzfi.Model.User;
import com.example.muzfi.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserProfileServiceImpl implements UserProfileService {

    private final UserRepository userRepository;

    @Autowired
    public UserProfileServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<UserProfileDto> getUserProfileByUserId(String id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

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
            userProfileDto.setCreatedDateTime(user.getCreatedDateTime());
            userProfileDto.setLastUpdatedDateTime(user.getLastUpdatedDateTime());

            return Optional.of(userProfileDto);

        } else {
            return Optional.empty();
        }
    }
}
