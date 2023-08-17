package com.example.muzfi.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "notifications")
public class Notification {
    @Id
    private String id;
    private Integer userId; // user to whom the notification belongs
    private String content; // content of the notification
    private boolean isRead; // to check if notification is read or not
    private LocalDateTime timestamp; // when the notification was generated

    public void setRead(boolean read) {
        this.isRead = true;
    }

}
