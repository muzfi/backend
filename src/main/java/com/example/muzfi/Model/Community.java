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

    public Community(String name, String title, String creatorId, String about) {
        this.name = name;
        this.title = title;
        this.creatorId = creatorId;
        this.about = about;
        this.subscriberIds = new ArrayList<>();
        this.postIds = new ArrayList<>();
    }
}