package com.example.muzfi.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

//TODO: Remove
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Integer id;

    private Integer user_id;
    private String content;
    private Integer likes;
    private Integer shares;

    //TODO: update with user date and time
    private LocalDateTime createdDateTime = LocalDateTime.now();
    private LocalDateTime updatedDateTime;
}
