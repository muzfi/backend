package com.example.muzfi.Dto.UserDto;


public class SignupConfirmationRequest {
    private String email;
    private String confirmationToken;

    public SignupConfirmationRequest() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getConfirmationToken() {
        return confirmationToken;
    }

    public void setConfirmationToken(String confirmationToken) {
        this.confirmationToken = confirmationToken;
    }
}
