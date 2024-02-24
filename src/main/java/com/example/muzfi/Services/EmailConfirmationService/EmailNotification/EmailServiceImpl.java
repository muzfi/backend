package com.example.muzfi.Services.EmailConfirmationService.EmailNotification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    // Assuming you have an email sender setup
    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendPasswordResetEmail(String email, String token) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Password Reset Request");
        message.setText("To reset your password, click the link below:\n" + "http://yourapp.com/reset-password?token=" + token);
        mailSender.send(message);
    }
}
