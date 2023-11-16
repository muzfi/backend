package com.example.muzfi.Services;

import org.springframework.stereotype.Service;

@Service
public class PushNotificationServiceImpl implements PushNotificationService{

        @Override
        public void sendPushNotification(String listingId) {
            // Replace with your actual push notification logic
            System.out.println("Push Notification: Your listing '" + listingId + "' has been posted!");
        }
}


