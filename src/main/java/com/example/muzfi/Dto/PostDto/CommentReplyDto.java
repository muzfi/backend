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
public class CommentReplyDto {
    private String postId;

    private String commentId;

    private String userId;
    private String repliedToUserId;


    private String text;

    private LocalDateTime createdDateTime;


}
