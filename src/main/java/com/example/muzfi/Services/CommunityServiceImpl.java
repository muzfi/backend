package com.example.muzfi.Services;
import com.example.muzfi.Dto.CommunityDto;
import com.example.muzfi.Model.Community;
import com.example.muzfi.Repository.CommunityRepository;
import com.example.muzfi.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommunityServiceImpl implements CommunityService {

    private final CommunityRepository communityRepository;
    private final UserService userService;

    @Autowired
    public CommunityServiceImpl(CommunityRepository communityRepository, UserService userService) {
        this.communityRepository = communityRepository;
        this.userService = userService;
    }

    @Override
    public CommunityDto createCommunity(String name, String title, String creatorId, String about) {
        Community community = communityRepository.save(new Community(name, title, creatorId, about));
        return mapToCommunityDto(community);
    }

    @Override
    public List<CommunityDto> getAllCommunities() {
        return communityRepository.findAll().stream()
                .map(this::mapToCommunityDto)
                .collect(Collectors.toList());
    }

    @Override
    public CommunityDto getCommunityByName(String name) {
        Community community = communityRepository.findByName(name);
        if (community == null) {
            throw new NotFoundException("Community not found with name: " + name);
        }
        return mapToCommunityDto(community);
    }

    @Override
    public void addUserToCommunity(String communityName, String userId) {
        Community community = communityRepository.findByName(communityName);
        if (community == null) {
            throw new NotFoundException("Community not found with name: " + communityName);
        }
        community.getSubscriberIds().add(userId);
        communityRepository.save(community);
    }

    private CommunityDto mapToCommunityDto(Community community) {
        CommunityDto dto = new CommunityDto();
        dto.setId(community.getId());
        dto.setName(community.getName());
        dto.setTitle(community.getTitle());
        dto.setCreatorId(community.getCreatorId());
        dto.setAbout(community.getAbout());
        dto.setSubscriberCount(community.getSubscriberIds().size());
        dto.setPostCount(community.getPostIds().size());
        return dto;
    }
}
