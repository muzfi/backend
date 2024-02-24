package com.example.muzfi.Dto.UserDto;

public class PasswordResetDto {
    private String token;
    private String newPassword;

    // Constructors, Getters and Setters
    public PasswordResetDto() {}

    public PasswordResetDto(String token, String newPassword) {
        this.token = token;
        this.newPassword = newPassword;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
