package com.example.muzfi.Dto.PostDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PostCreateDto {

    private String authorId;

    private String postTitle;

    private String postSubTitle;

    private String postTextContent;

    private Boolean isEnablePostReplyNotification;
}
