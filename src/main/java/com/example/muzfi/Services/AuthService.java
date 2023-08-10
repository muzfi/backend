package com.example.muzfi.Services;

public interface AuthService {
    void userRoleToElite(String userOktaId);

    void userRoleRemoveElite(String userOktaId);
}
