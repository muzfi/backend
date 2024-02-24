package com.example.muzfi.Services;

import com.example.muzfi.Model.ResetToken;
import com.example.muzfi.Model.User;
import com.example.muzfi.Repository.ResetTokenRepository;
import com.example.muzfi.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class ResetTokenService {

    @Autowired
    private static ResetTokenRepository resetTokenRepository;

    @Autowired
    private static UserRepository userRepository; // Assuming you have a user repository

    public static ResetToken createResetTokenForUser(User user) {
        ResetToken resetToken = new ResetToken();
        resetToken.setToken(UUID.randomUUID().toString());
        resetToken.setUserId(user.getId());
        resetToken.setExpiryDate(LocalDateTime.now().plusHours(24)); // Token expires in 24 hours

        return ResetToken.save(resetToken);
    }

    public static void createResetTokenForUser(Optional<User> user, String token) {

    }

    public static User validateResetToken(String token) {
        ResetToken resetToken = resetTokenRepository.findByToken(token);
        if (resetToken != null && resetToken.getExpiryDate().isAfter(Instant.from(LocalDateTime.now()))) {
            // Token is valid, find and return the associated user
            return userRepository.findById(resetToken.getUserId()).orElse(null);
        } else {
            // Token is invalid or expired
            return null;
        }
    }

    // Method to invalidate a token after use or if expired
    public void invalidateResetToken(String token) {
        ResetToken resetToken = resetTokenRepository.findByToken(token);
        if (resetToken != null) {
            resetTokenRepository.delete(resetToken);
        }
    }
}
