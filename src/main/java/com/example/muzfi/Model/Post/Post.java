package com.example.muzfi.Model.Post;

import com.example.muzfi.Model.Comment;
import com.example.muzfi.Model.Like;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Post {

    @Id
    private String id;

    private String authorId;
    private String postTitle;
    private String postSubTitle;
    private String postText;

    @DBRef
    private List<Like> likes;

    @DBRef
    private List<Comment> comments;

    private Integer shares;

    //TODO: update with user date and time
    private LocalDateTime createdDateTime = LocalDateTime.now();
    private LocalDateTime updatedDateTime;
}

