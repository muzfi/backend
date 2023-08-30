package com.example.muzfi.Dto.UserDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileLocationUpdateDto {
    private String location;

    private String country;

    private String state;

    private String city;
}
