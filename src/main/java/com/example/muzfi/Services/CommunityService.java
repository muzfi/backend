package com.example.muzfi.Services;

import com.example.muzfi.Dto.CommunityDto;
import jakarta.mail.MessagingException;
import com.example.muzfi.Model.Community;
import com.example.muzfi.Model.CommunitySettingsUpdate;

import java.util.List;

public interface CommunityService {

    CommunityDto createCommunity(CommunityDto communityDto) throws MessagingException;

    List<CommunityDto> getAllCommunities();

    CommunityDto getCommunityByName(String name);

    void addUserToCommunity(String communityName, String userId);

    List<CommunityDto> getSimilarCommunities(String communityName);

    void removeModerator(String communityName, String userId);

    void addMember(String communityName, String userId);

    void removeMember(String communityName, String userId);

    void restrictMember(String communityName, String userId);

    void addModerator(String communityName, String userId);

    Community updateCommunity(String id, CommunitySettingsUpdate communitySettingsUpdate);

    void deleteCommunity(String id);

}
