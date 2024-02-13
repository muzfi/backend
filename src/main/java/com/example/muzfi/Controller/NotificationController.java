package com.example.muzfi.Controller;

import com.example.muzfi.Model.Notification;
import com.example.muzfi.Services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/{userId}")
    public List<Notification> getNotificationsForUser(@PathVariable String userId) {
        return notificationService.getNotificationsForUser(userId);
    }

    @PutMapping("/read/{notificationId}")
    public void markNotificationAsRead(@PathVariable String notificationId) {
        notificationService.markNotificationAsRead(notificationId);
    }

    // Create a welcome notification
    @PostMapping("/welcome/{userId}")
    public Notification createWelcomeNotification(@PathVariable String userId) {
        return notificationService.createWelcomeNotification(userId);
    }

    // Create a listing added notification
    @PostMapping("/listing/{userId}")
    public Notification createListingAddedNotification(@PathVariable String userId) {
        return notificationService.createListingAddedNotification(userId);
    }

    // Create a replies received notification
    @PostMapping("/replies/{userId}")
    public Notification createRepliesNotification(@PathVariable String userId) {
        return notificationService.createRepliesNotification(userId);
    }

    // Create a message received notification
    @PostMapping("/messages/{userId}")
    public Notification createMessageReceivedNotification(@PathVariable String userId) {
        return notificationService.createMessageReceivedNotification(userId);
    }

    // Create an offer received notification
    @PostMapping("/offer/{userId}")
    public Notification createOfferReceivedNotification(@PathVariable String userId) {
        return notificationService.createOfferReceivedNotification(userId);
    }

    // Create an offer declined notification
    @PostMapping("/offer-declined/{userId}")
    public Notification createOfferDeclinedNotification(@PathVariable String userId) {
        return notificationService.createOfferDeclinedNotification(userId);
    }

    // Create a cart item notification
    @PostMapping("/cart-item/{userId}")
    public Notification createCartItemNotification(@PathVariable String userId) {
        return notificationService.createCartItemNotification(userId);
    }

    // Create a new post notification
    @PostMapping("/post-created/{userId}")
    public Notification createPostCreationNotification(@PathVariable String userId) {
        return notificationService.createPostCreationNotification(userId);
    }

    // Listing purchased notification
    @PostMapping("/listing-purchased/{userId}")
    public Notification createListingPurchasedNotification(@PathVariable String userId) {
        return notificationService.createListingPurchasedNotification(userId);
    }

    // Leave feedback reminder notification
    @PostMapping("/feedback-reminder/{userId}")
    public Notification createFeedbackReminderNotification(@PathVariable String userId) {
        return notificationService.createFeedbackReminderNotification(userId);
    }

    // Successful purchase notification
    @PostMapping("/purchase-success/{userId}")
    public Notification createPurchaseSuccessNotification(@PathVariable String userId) {
        return notificationService.createPurchaseSuccessNotification(userId);
    }

    // Order shipped notification
    @PostMapping("/order-shipped/{userId}")
    public Notification createOrderShippedNotification(@PathVariable String userId) {
        return notificationService.createOrderShippedNotification(userId);
    }

    // Order delivered notification
    @PostMapping("/order-delivered/{userId}")
    public Notification createOrderDeliveredNotification(@PathVariable String userId) {
        return notificationService.createOrderDeliveredNotification(userId);
    }

    // Order canceled notification
    @PostMapping("/order-canceled/{userId}")
    public Notification createOrderCanceledNotification(@PathVariable String userId) {
        return notificationService.createOrderCanceledNotification(userId);
    }}
