package com.example.muzfi.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.time.LocalDateTime;

@Document(collection = "resetTokens")
public class ResetToken {
    @Id
    private String id;
    private String token;
    private String userId;
    private LocalDateTime expiryDate;

    public static ResetToken save(ResetToken resetToken) {
        return null;
    }

    public void setExpiryDate(LocalDateTime localDateTime) {
    }

    public void setUserId(String id) {
    }

    public void setToken(String string) {
    }

    public Instant getExpiryDate() {
        return null;
    }

    public String getUserId() {
        return null;
    }

    // Constructors, Getters, and Setters
}
