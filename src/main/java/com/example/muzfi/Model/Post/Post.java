package com.example.muzfi.Model.Post;

import com.example.muzfi.Enums.PostType;
import com.example.muzfi.Model.Comment;
import com.example.muzfi.Model.Like;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "posts")
public class Post {

    @Id
    private String id;

    private String authorId;

    private PostType postType;

    private String postTypeId;

    @DBRef
    private List<Like> likes;

    @DBRef
    private List<Comment> comments;

    private Integer shares;

    private Boolean isEnablePostReplyNotification;

    private Boolean isDraft;

    private LocalDateTime createdDateTime;

    private LocalDateTime updatedDateTime;
}

