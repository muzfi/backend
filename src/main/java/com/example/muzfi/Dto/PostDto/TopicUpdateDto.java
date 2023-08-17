package com.example.muzfi.Dto.PostDto;

import com.example.muzfi.Enums.PostCategory;
import com.example.muzfi.Enums.TopicType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TopicUpdateDto {
    private String id;

    private String postId;

    private String title;

    private TopicType topicType;

    private String text;

    private List<PostCategory> postCategory;

    private List<String> tags;
}
