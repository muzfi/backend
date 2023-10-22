package com.example.muzfi.Dto.UserDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserBasicDto {
    private String id;

    private String userName;

    private String firstName;

    private String lastName;

    private String displayName;

    private String profileUrl;
}
