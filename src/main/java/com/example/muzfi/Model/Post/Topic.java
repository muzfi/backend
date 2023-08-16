package com.example.muzfi.Model.Post;

import com.example.muzfi.Enums.PostCategory;
import com.example.muzfi.Enums.TopicType;
import jakarta.validation.constraints.NotNull;
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
@Document(collection = "topics")
public class Topic {
    @Id
    private String id;

    private String postId;

    private String authorId;

    @NotNull
    private String title;

    private TopicType topicType;

    private String text;

    private List<PostCategory> postCategory;

    private List<String> tags;

    private LocalDateTime createdDateTime;

    private LocalDateTime updatedDateTime;
}
