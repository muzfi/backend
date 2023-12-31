package com.example.muzfi.Model;

import com.example.muzfi.Enums.UserGender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "user_settings")
public class UserSetting {
    @Id
    private String id;

    private String userId;

    private String displayLang;

    private String contentLang;

    private Boolean isAllowToFollow;

    private Boolean isContentVisible;

    private Boolean isActiveInCommunityVisible;

    private Boolean isShowsUpInSearchResults;

    //TODO: update with user date and time
    private LocalDateTime createdDateTime;

    private LocalDateTime lastUpdatedDateTime;

    private String email;
    private UserGender gender;
    private String country;
    private String state;
    private String city;

}
