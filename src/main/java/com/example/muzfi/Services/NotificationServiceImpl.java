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
}
