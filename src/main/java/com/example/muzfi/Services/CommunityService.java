package com.example.muzfi.Services;

import com.example.muzfi.Dto.CommunityDto;
import com.example.muzfi.Model.Community;

import java.util.List;

public interface CommunityService {

    CommunityDto createCommunity(String name, String title, String creatorId, String about);

    List<CommunityDto> getAllCommunities();

    CommunityDto getCommunityByName(String name);

    void addUserToCommunity(String communityName, String userId);
}
