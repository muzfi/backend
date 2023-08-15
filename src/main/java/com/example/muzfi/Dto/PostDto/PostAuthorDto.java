package com.example.muzfi.Dto.PostDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PostAuthorDto {
    private String id;

    private String username;

    private String avatar;

    private String name;

    private double rating;

    private int reviewCount;

    private String location;

    private int muzPoints;

    private double sellerRating;
}
