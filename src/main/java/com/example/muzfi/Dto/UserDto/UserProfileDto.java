package com.example.muzfi.Dto.UserDto;

import com.example.muzfi.Enums.UserGender;
import com.example.muzfi.Model.SocialLink;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileDto {
    private String userName;

    private String email;

    private String firstName;

    private String lastName;

    private String displayName;

    private UserGender gender;

    private String description;

    private String location;

    private String country;

    private String state;

    private String city;

    private String profileUrl;

    private String bannerImageUrl;

    private int noOfPosts;

    private int noOfGears;

    private int noOfSales;

    private int muzPoints;

    private LocalDate birthDate;

    private List<SocialLink> socialLinks;

    private double sellerRatings;

    private double buyerRatings;

    private Boolean isFollowed;

    private Boolean isBlocked;

    private LocalDateTime createdDateTime;

    private LocalDateTime lastUpdatedDateTime;
}
