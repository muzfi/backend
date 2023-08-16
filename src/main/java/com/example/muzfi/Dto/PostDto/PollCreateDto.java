package com.example.muzfi.Dto.PostDto;

import com.example.muzfi.Enums.PostCategory;
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
public class PollCreateDto extends PostCreateDto {
    private List<String> options;

    private LocalDateTime deadline;

    private List<PostCategory> postCategories;
}
