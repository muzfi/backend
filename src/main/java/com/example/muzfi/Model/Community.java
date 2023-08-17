package com.example.muzfi.Model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "communities")
public class Community {

    @Id
    private String id;
    private String name;
    private String title;
    private String creatorId;
    private LocalDateTime createdDateTime = LocalDateTime.now();
    private List<String> subscriberIds;
    private List<String> postIds;
    private String about;
    private String sub;
    private String type;
    private String genre;
    private boolean joinable;
    private boolean creatable;
    private List<String> reviews;
    private List<String> similarCommunityIds;
    private String rules;
    private int subscriberCount;
    private int rankedSize;
    private List<String> moderators;

    public Community(String name, String title, String creatorId, LocalDateTime createdDateTime, List<String> subscriberIds, List<String> postIds, String about, String sub, String type, String genre, boolean joinable, boolean creatable, List<String> reviews, List<String> similarCommunityIds, String rules, int subscriberCount, int rankedSize, List<String> moderators) {
        this.name = name;
        this.title = title;
        this.creatorId = creatorId;
        this.createdDateTime = createdDateTime;
        this.subscriberIds = subscriberIds;
        this.postIds = postIds;
        this.about = about;
        this.sub = sub;
        this.type = type;
        this.genre = genre;
        this.joinable = joinable;
        this.creatable = creatable;
        this.reviews = reviews;
        this.similarCommunityIds = similarCommunityIds;
        this.rules = rules;
        this.subscriberCount = subscriberCount;
        this.rankedSize = rankedSize;
        this.moderators = moderators;
    }
}