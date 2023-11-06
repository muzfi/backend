package com.example.muzfi.Dto.PostDto;

import com.example.muzfi.Model.Post.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ListingFeedDto {

    private String id;

    private String title;

    private String subTitle;

    private String description;

    private List<String> images;

    private BigDecimal price;

    private String condition;

    private int bidsCount;

    private LocalDateTime deadline;

    private List<String> tags;

    private Comment comment;

    private LocalDateTime createdDateTime;

    private LocalDateTime updatedDateTime;
}
