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
    }

    // In NotificationController class

    // Endpoint for creating a notification when a note/message is left regarding an order
    @PostMapping("/order-note/{userId}")
    public Notification createOrderNoteNotification(@PathVariable String userId) {
        return notificationService.createOrderNoteNotification(userId);
    }

    // Endpoint for creating a notification when a refund submission is sent
    @PostMapping("/refund-submission/{userId}")
    public Notification createRefundSubmissionNotification(@PathVariable String userId) {
        return notificationService.createRefundSubmissionNotification(userId);
    }

    // Endpoint for creating a notification when a refund is accepted
    @PostMapping("/refund-accepted/{userId}")
    public Notification createRefundAcceptedNotification(@PathVariable String userId) {
        return notificationService.createRefundAcceptedNotification(userId);
    }

    // Endpoint for creating a notification when a refund is declined
    @PostMapping("/refund-declined/{userId}")
    public Notification createRefundDeclinedNotification(@PathVariable String userId) {
        return notificationService.createRefundDeclinedNotification(userId);
    }

    // Endpoint for creating a notification when an order has been refunded
    @PostMapping("/order-refunded/{userId}")
    public Notification createOrderRefundedNotification(@PathVariable String userId) {
        return notificationService.createOrderRefundedNotification(userId);
    }

    // Endpoint for creating a notification for unread messages
    @PostMapping("/unread-messages/{userId}")
    public Notification createUnreadMessagesNotification(@PathVariable String userId) {
        return notificationService.createUnreadMessagesNotification(userId);
    }

    // Endpoint for creating a notification when a community is successfully created
    @PostMapping("/community-created/{userId}")
    public Notification createCommunityCreatedNotification(@PathVariable String userId) {
        return notificationService.createCommunityCreatedNotification(userId);
    }

    @PostMapping("/added-to-gear-room/{userId}")
    public Notification createAddedToGearRoomNotification(@PathVariable String userId) {
        return notificationService.createAddedToGearRoomNotification(userId);
    }

    @PostMapping("/cart-item-unavailable/{userId}")
    public Notification createCartItemUnavailableNotification(@PathVariable String userId) {
        return notificationService.createCartItemUnavailableNotification(userId);
    }

    @PostMapping("/earn-points/{userId}")
    public Notification createEarnPointsNotification(@PathVariable String userId) {
        return notificationService.createEarnPointsNotification(userId);
    }

    @PostMapping("/payout-sent/{userId}")
    public Notification createPayoutSentNotification(@PathVariable String userId) {
        return notificationService.createPayoutSentNotification(userId);
    }

    @PostMapping("/get-subscription/{userId}")
    public Notification createGetSubscriptionNotification(@PathVariable String userId) {
        return notificationService.createGetSubscriptionNotification(userId);
    }

    @PostMapping("/update-profile/{userId}")
    public Notification createUpdateProfileNotification(@PathVariable String userId) {
        return notificationService.createUpdateProfileNotification(userId);
    }

    @PostMapping("/community-invite/{userId}")
    public Notification createInviteToCommunityNotification(@PathVariable String userId) {
        return notificationService.createInviteToCommunityNotification(userId);
    }

}
