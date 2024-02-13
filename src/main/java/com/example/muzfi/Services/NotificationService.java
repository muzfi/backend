package com.example.muzfi.Services;

import com.example.muzfi.Model.Notification;

import java.util.List;

public interface NotificationService {
    List<Notification> getNotificationsForUser(String userId);
    Notification createNotification(Notification notification);
    void markNotificationAsRead(String notificationId);

    Notification createCartItemNotification(String userId);

    Notification createOfferDeclinedNotification(String userId);

    Notification createWelcomeNotification(String userId);

    Notification createListingAddedNotification(String userId);

    Notification createRepliesNotification(String userId);

    Notification createMessageReceivedNotification(String userId);

    Notification createOfferReceivedNotification(String userId);

    Notification createOrderCanceledNotification(String userId);

    Notification createPostCreationNotification(String userId);

    Notification createFeedbackReminderNotification(String userId);

    Notification createPurchaseSuccessNotification(String userId);

    Notification createOrderShippedNotification(String userId);

    Notification createOrderDeliveredNotification(String userId);

    Notification createListingPurchasedNotification(String userId);
}
