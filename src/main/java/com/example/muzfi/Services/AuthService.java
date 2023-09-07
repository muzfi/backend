package com.example.muzfi.Services;

import com.example.muzfi.Model.User;

public interface AuthService {
    boolean isLoggedInUser(String userId);

    boolean isElite();

    boolean isAdmin();

    User getLoggedInUser();

    void userRoleToElite(String userOktaId);

    void userRoleRemoveElite(String userOktaId);
}
