package com.example.muzfi.Model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "posts")
public class Post {

    @Id
    private String id;

    private Integer user_id;
    private String content;
    private Integer likes;
    private Integer shares;

    //TODO: update with user date and time
    private LocalDateTime createdDateTime = LocalDateTime.now();
    private LocalDateTime updatedDateTime;
}
