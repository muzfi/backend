package com.example.muzfi.Dto.UserDto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserSignupDto {
    private String username;
    private String email;
    private String password;


}
