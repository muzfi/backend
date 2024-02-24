package com.example.muzfi.Services.EmailConfirmationService.EmailNotification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

public interface EmailService {
    void sendPasswordResetEmail(String email, String token);
}

