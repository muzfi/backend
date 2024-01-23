package com.example.muzfi.Services.EmailConfirmationService.EmailNotification;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public void sendWelcomeEmail(String recipientEmail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipientEmail);
        message.setSubject("Welcome to Our Platform!");
        message.setText("Thank you for registering. Welcome to our platform!");

        emailSender.send(message);
    }

    public void sendPasswordResetEmail(String recipientEmail, String resetLink) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipientEmail);
        message.setSubject("Password Reset Request");
        message.setText("To reset your password, please click on the following link: " + resetLink);

        emailSender.send(message);
    }

    // ... Implement other methods similarly ...

    public void sendOrderConfirmationEmail(String recipientEmail, String itemName) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipientEmail);
        message.setSubject("Order Confirmation");
        message.setText("Your order for " + itemName + " has been confirmed.");

        emailSender.send(message);
    }

    public void sendItemSoldEmail(String recipientEmail, String itemName) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipientEmail);
        message.setSubject("Item Sold: " + itemName);
        message.setText("Congratulations! Your item, " + itemName + ", has been sold. Time to ship!");

        emailSender.send(message);
    }


    public void sendRefundAcceptedEmail(String recipientEmail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipientEmail);
        message.setSubject("Refund Accepted");
        message.setText("Your refund request has been accepted. Please ship the item back.");

        emailSender.send(message);
    }

    public void sendOfferAlert(String recipientEmail, String itemName) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipientEmail);
        message.setSubject("New Offer Received");
        message.setText("You have received a new offer for your listing item: " + itemName);

        emailSender.send(message);
    }

    public void sendOfferAcceptedAlert(String recipientEmail, String itemName) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipientEmail);
        message.setSubject("Offer Accepted");
        message.setText("Your offer for " + itemName + " has been accepted. It's time to complete your purchase.");

        emailSender.send(message);
    }

    public void sendOfferDeclinedAlert(String recipientEmail, String itemName) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipientEmail);
        message.setSubject("Offer Declined");
        message.setText("Your offer for " + itemName + " has been declined.");

        emailSender.send(message);
    }

    public void sendRefundRequestSubmittedAlert(String recipientEmail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipientEmail);
        message.setSubject("Refund Request Submitted");
        message.setText("Your refund request has been submitted and is being reviewed.");

        emailSender.send(message);
    }

    public void sendRefundRequestToSellerAlert(String sellerEmail, String reason) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(sellerEmail);
        message.setSubject("Refund Request Received");
        message.setText("A buyer has requested a refund for their purchase. Reason: " + reason);

        emailSender.send(message);
    }

    public void sendRefundDeclinedAlert(String recipientEmail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipientEmail);
        message.setSubject("Refund Request Declined");
        message.setText("Your refund request has been reviewed and declined.");

        emailSender.send(message);
    }

    public void sendOrderDeliveredAlert(String recipientEmail, String itemName) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipientEmail);
        message.setSubject("Order Delivered");
        message.setText("Your order for " + itemName + " has been delivered.");

        emailSender.send(message);
    }

    public void sendPayoutConfirmationAlert(String sellerEmail, double amount) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(sellerEmail);
        message.setSubject("Payout Confirmation");
        message.setText("Your payout of $" + amount + " has been processed and sent.");

        emailSender.send(message);
    }

    public void sendUnreadMessagesAlert(String recipientEmail, int messageCount) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipientEmail);
        message.setSubject("Unread Messages Alert");
        message.setText("You have " + messageCount + " unread messages in your inbox.");

        emailSender.send(message);
    }

    public void sendUncheckedNotificationsAlert(String recipientEmail, int notificationCount) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipientEmail);
        message.setSubject("Unread Notifications Alert");
        message.setText("You have " + notificationCount + " unchecked notifications.");

        emailSender.send(message);
    }

    public void sendReturnRequestSentAlert(String buyerEmail, String itemName) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(buyerEmail);
        message.setSubject("Return Request Sent");
        message.setText("Your return request for the item '" + itemName + "' has been sent successfully. Please wait for the seller's response.");

        emailSender.send(message);
    }

    public void sendReturnRequestReceivedAlert(String sellerEmail, String itemName, String buyerName) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(sellerEmail);
        message.setSubject("Return Request Received");
        message.setText("You have received a return request from " + buyerName + " for the item '" + itemName + "'. Please review the request and respond accordingly.");

        emailSender.send(message);
    }
}
