package com.example.muzfi.Model;

import com.example.muzfi.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

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

    private List<UserRole> role;

    private String description;

    private LocalDate birthDate;

    private String location;

    private String profilePicUri;
}
