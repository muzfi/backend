package com.example.muzfi.Dto.PostDto;

import com.example.muzfi.Enums.PostCategory;
import com.example.muzfi.Model.Post.PollOption;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PollDetailsDto {
    private String id;

    private String postId;

    private String authorId;

    private String title;

    private String text;

    private List<PollOption> pollOptions;

    private LocalDateTime pollDeadline;

    private List<PostCategory> postCategories;

    private List<String> tags;

    private Boolean isVoted;

    private LocalDateTime createdDateTime;

    private LocalDateTime updatedDateTime;
}
