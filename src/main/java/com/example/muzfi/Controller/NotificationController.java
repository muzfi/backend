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
    public List<Notification> getNotificationsForUser(@PathVariable Integer userId) {
        return notificationService.getNotificationsForUser(userId);
    }

    @PutMapping("/read/{notificationId}")
    public void markNotificationAsRead(@PathVariable String notificationId) {
        notificationService.markNotificationAsRead(notificationId);
    }

    // Add other endpoints as needed
}
