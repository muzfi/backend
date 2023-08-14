package com.example.muzfi.Dto.PostDto;

import com.example.muzfi.Model.Comment;
import com.example.muzfi.Model.Like;
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
public class PostDetailsDto {

    private String id;

    private String authorId;

    private String postTitle;

    private String postSubTitle;

    private String postTextContent;

    private String postType;

    private String postTypeId;

    private Object postTypeData;

    private List<Like> likes;

    private List<Comment> comments;

    private Integer shares;

    private LocalDateTime createdDateTime;

    private LocalDateTime updatedDateTime;
}
