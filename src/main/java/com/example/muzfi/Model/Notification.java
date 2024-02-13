package com.example.muzfi.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "notifications")
public class Notification {
    @Id
    private String id;
    private String userId; // Updated to String to match your service implementation
    private String content; // Renamed from 'message' to 'content' for consistency
    private boolean isRead;
    private LocalDateTime timestamp; // Timestamp of when the notification was generated

    // Constructors
    public Notification() {
    }

    public Notification(String userId, String content, boolean isRead) {
        this.userId = userId;
        this.content = content;
        this.isRead = isRead;
        this.timestamp = LocalDateTime.now(); // Automatically set the timestamp when creating a new notification
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isRead() {
        return isRead;
    }

    // Corrected to actually use the parameter 'read'
    public void setRead(boolean read) {
        this.isRead = read;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public void setMessage(String s) {
    }

    // Additional methods can be added as needed
}
