package com.example.muzfi.notificationComponent;

import com.example.muzfi.Model.Post.ListingCreatedEvent;
import com.example.muzfi.Services.PushNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Component
public class ListingEventListener {

    private final JavaMailSender javaMailSender;
    private final PushNotificationService pushNotificationService; // Add this line

    @Autowired
    public ListingEventListener(JavaMailSender javaMailSender, PushNotificationService pushNotificationService) {
        this.javaMailSender = javaMailSender;
        this.pushNotificationService = pushNotificationService;
    }

    @EventListener
    public void handleListingCreatedEvent(ListingCreatedEvent event) {
        String listingId = event.getListingId();

        // Email notification logic
        sendEmailNotification(listingId);

        // Push notification logic
        sendPushNotification(listingId);
    }

    private void sendEmailNotification(String listingId) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("recipient@example.com"); // Replace with the recipient's email address
        message.setSubject("Listing Posted Notification");
        message.setText("Your listing '" + listingId + "' has been posted!");

        javaMailSender.send(message);
    }

    private void sendPushNotification(String listingId) {
        // Replace with your push notification logic
        pushNotificationService.sendPushNotification(listingId);
    }
}
