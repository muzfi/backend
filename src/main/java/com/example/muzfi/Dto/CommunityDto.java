package com.example.muzfi.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CommunityDto {

    private String id;
    private String name;
    private String title;
    private String creatorId;
    private String about;
    private int subscriberCount;
    private int postCount;
}
