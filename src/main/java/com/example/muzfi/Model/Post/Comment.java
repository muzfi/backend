package com.example.muzfi.Model.Post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "comments")
public class Comment {
    @Id
    private String id;

    private String postId;

    private String userId;

    private String text;

    private List<String> replyCommentIds;

    private Boolean isParentComment;

    private LocalDateTime createdDateTime;

    private LocalDateTime updatedDateTime;
}
