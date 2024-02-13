package com.example.muzfi.Services;

import com.example.muzfi.Model.Notification;
import com.example.muzfi.Repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public List<Notification> getNotificationsForUser(String userId) {
        return notificationRepository.findAllByUserId(userId);
    }

    @Override
    public Notification createNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    @Override
    public void markNotificationAsRead(String notificationId) {
        Notification notification = notificationRepository.findById(notificationId).orElse(null);
        if(notification != null) {
            notification.setRead(true);
            notificationRepository.save(notification);
        }
    }
    // Welcome notification
    public Notification createWelcomeNotification(String userId) {
        Notification notification = new Notification();
        notification.setUserId(userId);
        notification.setMessage("Welcome to the platform, invite others to earn muzPoints…");
        notification.setRead(false);
        return createNotification(notification);
    }

    // Notification for successful listing addition
    public Notification createListingAddedNotification(String userId) {
        Notification notification = new Notification();
        notification.setUserId(userId);
        notification.setMessage("Your listings have been added to marketplace successfully");
        notification.setRead(false);
        return createNotification(notification);
    }

    // Notification for received replies
    public Notification createRepliesNotification(String userId) {
        Notification notification = new Notification();
        notification.setUserId(userId);
        notification.setMessage("Your post has received replies, check your replies…");
        notification.setRead(false);
        return createNotification(notification);
    }

    // Notification for received messages
    public Notification createMessageReceivedNotification(String userId) {
        Notification notification = new Notification();
        notification.setUserId(userId);
        notification.setMessage("You have received messages");
        notification.setRead(false);
        return createNotification(notification);
    }

    // Notification for received offer/counteroffer
    public Notification createOfferReceivedNotification(String userId) {
        Notification notification = new Notification();
        notification.setUserId(userId);
        notification.setMessage("You have received an offer or counteroffer");
        notification.setRead(false);
        return createNotification(notification);
    }

    // Notification for declined offer
    public Notification createOfferDeclinedNotification(String userId) {
        Notification notification = new Notification();
        notification.setUserId(userId);
        notification.setMessage("Your offer has been declined");
        notification.setRead(false);
        return createNotification(notification);
    }

    // Notification for item in cart
    public Notification createCartItemNotification(String userId) {
        Notification notification = new Notification();
        notification.setUserId(userId);
        notification.setMessage("You have an item in your cart");
        notification.setRead(false);
        return createNotification(notification);
    }

    public Notification createPostCreationNotification(String userId) {
        return createNotification(new Notification(userId, "You have successfully created a new post", false));
    }

    // Notification for a purchased listing
    public Notification createListingPurchasedNotification(String userId) {
        return createNotification(new Notification(userId, "Your listing has been purchased, time to ship", false));
    }

    // Notification to remind leaving feedback
    public Notification createFeedbackReminderNotification(String userId) {
        return createNotification(new Notification(userId, "Leave feedback of your last order (both seller and buyer)", false));
    }

    // Notification for a successful purchase
    public Notification createPurchaseSuccessNotification(String userId) {
        return createNotification(new Notification(userId, "Successfully purchased", false));
    }

    // Notification that an order has been shipped
    public Notification createOrderShippedNotification(String userId) {
        return createNotification(new Notification(userId, "Your order has been shipped", false));
    }

    // Notification that an order has been delivered
    public Notification createOrderDeliveredNotification(String userId) {
        return createNotification(new Notification(userId, "Your order has been delivered", false));
    }

    // Notification that an order has been canceled
    public Notification createOrderCanceledNotification(String userId) {
        return createNotification(new Notification(userId, "Your order has been canceled", false));
    }
}
