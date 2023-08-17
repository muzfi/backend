package com.example.muzfi.Services;

import com.example.muzfi.Model.Notification;

import java.util.List;

public interface NotificationService {
    List<Notification> getNotificationsForUser(Integer userId);
    Notification createNotification(Notification notification);
    void markNotificationAsRead(String notificationId);
}
