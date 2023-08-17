package com.example.muzfi.Services;

import com.example.muzfi.Dto.CommunityDto;
import com.example.muzfi.Model.Community;

import java.util.List;

public interface CommunityService {

    CommunityDto createCommunity(CommunityDto communityDto);

    List<CommunityDto> getAllCommunities();

    CommunityDto getCommunityByName(String name);

    void addUserToCommunity(String communityName, String userId);

    List<CommunityDto> getSimilarCommunities(String communityName);
}
