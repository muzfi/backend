package com.example.muzfi.Services.EmailConfirmationService.EmailNotification;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailNotificationService {
    @Autowired
    private JavaMailSender emailSender;

    public void sendNewOfferNotification(String recipientEmail, String listingTitle) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipientEmail);
        message.setSubject("New Offer on Your Listing: " + listingTitle);
        message.setText("You have received a new offer on your listing. Log in to check the details.");

        emailSender.send(message);
    }

    public void sendEmailForCommentReply(String to, String subject, String message) {
        try {
            MimeMessage mimeMessage = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(message, true);

            emailSender.send(mimeMessage);
        } catch (MessagingException e) {

            e.printStackTrace();
        }
    }

    public void sendMessage(String recipient, String subject, String message) {
        try {
            MimeMessage mimeMessage = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setTo(recipient);
            helper.setSubject(subject);
            helper.setText(message, true);

            emailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void sendItemSoldNotification(String recipientEmail, String itemName) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipientEmail);
        message.setSubject("Item Sold: " + itemName);
        message.setText("Congratulations! Your item, " + itemName + ", has been sold.");

        emailSender.send(message);
    }

    public void sendInvitationEmail(String email) {
        String subject = "Invitation to Join Muzfi";
        String message = "You have been invited to join Muzfi! Click the link below to create your account:\n\n"
                + "https://example.com/registration?email=" + email;  //Change with absolute url

        sendMessage(email, subject, message);
    }
}
