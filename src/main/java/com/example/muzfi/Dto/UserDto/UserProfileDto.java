package com.example.muzfi.Dto.UserDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileDto {
    private String userName;

    private String email;

    private String firstName;

    private String lastName;

    private String description;

    private String location;

    private String profileUrl;

    private int noOfPosts;

    private int noOfGears;

    private int noOfSales;

    private int muzPoints;

    private LocalDate birthDate;

    private double sellerRatings;

    private double buyerRatings;

    private Boolean isFollowed;

    private Boolean isBlocked;

    private LocalDateTime createdDateTime;

    private LocalDateTime lastUpdatedDateTime;
}
