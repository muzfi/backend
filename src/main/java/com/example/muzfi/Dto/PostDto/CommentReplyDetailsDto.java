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
public class CommentReplyDetailsDto {
    private String id;

    private String userId;

    private String text;

    private LocalDateTime createdDateTime;

    private LocalDateTime updatedDateTime;
}
