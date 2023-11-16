package com.example.muzfi.Model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;

@Getter
@Setter
@Document
public class Catalog {
    @Id
    private String id;
    private String title;
}
