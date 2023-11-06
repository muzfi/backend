package com.example.muzfi.Model;

import lombok.Data;
import org.bson.types.Binary;

@Data
public class CommunitySettingsUpdate {
    private String communityName;
    private String subCategory;
    private String description;
    private String country;
    private String privacy;
    private Binary communityImage;
}
