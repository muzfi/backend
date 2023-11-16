package com.example.muzfi.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class ChatMessagingSetting {
    @Id
    private String userId;
    private boolean allowPrivateMessages;
    private boolean markAllAsRead;
}
