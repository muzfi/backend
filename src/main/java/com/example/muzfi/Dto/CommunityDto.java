package com.example.muzfi.Dto;

import com.example.muzfi.Model.CommunityRule;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.Binary;

import java.util.List;

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
    private String sub;
    private String type;
    private String genre;
    private boolean joinable;
    private boolean creatable;
    private List<String> reviews;
    private List<String> similarCommunityIds;
    private CommunityRule rules;
    private int rankedSize;
    private List<String> moderators;
    private String country;
    private Binary communityImage;
    private String description;
}
