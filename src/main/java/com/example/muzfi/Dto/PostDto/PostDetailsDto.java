package com.example.muzfi.Dto.PostDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PostDetailsDto {

    private String id;

    private PostAuthorDto author;

    private String type;

    private Object postContent;

    private Integer likes;

    private Integer comments;

    private Integer shares;

    private Boolean isDraft;

    private LocalDateTime createdDateTime;

    private LocalDateTime updatedDateTime;
}
