package com.example.muzfi.Model;

import com.example.muzfi.Model.Post.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "reviews")
public class Review {
    @Id
    private String id;

    private String text;

    private Integer userID;

    @DBRef
    private Post post;

    //TODO: update with user date and time
    private LocalDateTime createdDateTime = LocalDateTime.now();
    private LocalDateTime updatedDateTime;
}
