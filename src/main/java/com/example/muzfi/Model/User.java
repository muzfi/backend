package com.example.muzfi.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "users")
public class User {
    @Id
    private String id;

    private String oktaId;

    private String email;

    private String userName;

    private String firstName;

    private String lastName;

    private String role;

    private String description;

    private LocalDate birthDate;

    private String location;

    private String profilePicUri;
}
