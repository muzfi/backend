package com.example.muzfi.Services;

import com.example.muzfi.Dto.UserDto.LoginDto;
import com.example.muzfi.Dto.UserDto.UserSignupDto;
import com.example.muzfi.Model.User;

public interface AuthService {

     User signUp(UserSignupDto userSignupDto);


    boolean isLoggedInUser(String userId);

    boolean isElite();

    boolean isAdmin();

    User getLoggedInUser();

    void userRoleToElite(String userOktaId);

    void userRoleRemoveElite(String userOktaId);
}
