package com.example.muzfi.Model;

import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.Setter;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@AllArgsConstructor
@Document
public class Invitation {
    @Id
    private String id;
    private String senderId;
    private String recipientEmail;
    private String confirmationToken;
    private boolean confirmed;

    public Invitation(String senderId, String recipientEmail, String confirmationToken) {
    }
}
