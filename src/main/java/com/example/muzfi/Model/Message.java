package com.example.muzfi.Model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "messages")
public class Message {
    @Id
    private String id;
    private String sender;
    private String recipient;
    private String content;
    // Getters and setters
}
