package com.example.muzfi.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class NotificationSettings {
    @Id
    private String userId;
    private boolean inboxMessages;
    private boolean chatMessages;
    private boolean activity;
    private boolean mentions;
    private boolean commentsOnPosts;
    private boolean upvoteOnPosts;
    private boolean upvoteOnComments;
    private boolean repliesToComments;
    private boolean activityOnComments;
    private boolean activityOnThreads;
    private boolean activityOnChatPosts;
    private boolean offersMadeAccepted;
    private boolean orders;
}
