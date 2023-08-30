package com.example.muzfi.Dto.UserDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileUpdateDto {
    private String firstName;

    private String lastName;

    private String description;

    private LocalDate birthDate;

    private String profilePicUri;
}
