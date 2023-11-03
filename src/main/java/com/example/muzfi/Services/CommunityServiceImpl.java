package com.example.muzfi.Services;
import com.example.muzfi.Dto.CommunityDto;
import com.example.muzfi.Model.Community;
import com.example.muzfi.Repository.CommunityRepository;
import com.example.muzfi.Services.EmailConfirmationService.EmailConfirmationService;
import com.example.muzfi.Services.User.UserService;
import com.example.muzfi.exception.NotFoundException;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommunityServiceImpl implements CommunityService {

    private final CommunityRepository communityRepository;
    private final UserService userService;
    private final EmailConfirmationService emailConfirmationService;

    @Autowired
    public CommunityServiceImpl(CommunityRepository communityRepository, UserService userService, EmailConfirmationService emailConfirmationService) {
        this.communityRepository = communityRepository;
        this.userService = userService;
        this.emailConfirmationService = emailConfirmationService;
    }

    @Override
    public CommunityDto createCommunity(CommunityDto communityDto) throws MessagingException {
        Community community = new Community(
                communityDto.getName(),
                communityDto.getTitle(),
                communityDto.getCreatorId(),
                LocalDateTime.now(),
                new ArrayList<>(),
                new ArrayList<>(),
                communityDto.getAbout(),
                communityDto.getSub(),
                communityDto.getType(),
                communityDto.getGenre(),
                communityDto.isJoinable(),
                communityDto.isCreatable(),
                new ArrayList<>(),
                new ArrayList<>(),
                communityDto.getRules(),
                0,
                0,
                new ArrayList<>()
        );

        community = communityRepository.save(community);
        // Send the community created email confirmation
        emailConfirmationService.sendCommunityCreatedConfirmation(communityDto.getCreatorId(), communityDto.getName());

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

    @Override
    public List<CommunityDto> getSimilarCommunities(String communityName) {
        Community community = communityRepository.findByName(communityName);
        if (community == null) {
            throw new NotFoundException("Community not found with name: " + communityName);
        }

        List<Community> similarCommunities = communityRepository.findByTypeAndGenre(
                community.getType(),
                community.getGenre()
        );

        List<CommunityDto> similarCommunityDtos = similarCommunities.stream()
                .filter(c -> !c.getName().equals(community.getName()))
                .map(this::mapToCommunityDto)
                .collect(Collectors.toList());

        return similarCommunityDtos;
    }

    @Override
    public void addModerator(String communityName, String userId) {
        Community community = communityRepository.findByName(communityName);
        if (community == null) {
            throw new NotFoundException("Community not found with name: " + communityName);
        }
        community.getModerators().add(userId);
        communityRepository.save(community);
    }

    @Override
    public void removeModerator(String communityName, String userId) {
        Community community = communityRepository.findByName(communityName);
        if (community == null) {
            throw new NotFoundException("Community not found with name: " + communityName);
        }
        community.getModerators().remove(userId);
        communityRepository.save(community);
    }

    @Override
    public void addMember(String communityName, String userId) {
        Community community = communityRepository.findByName(communityName);
        if (community == null) {
            throw new NotFoundException("Community not found with name: " + communityName);
        }
        community.getSubscriberIds().add(userId);
        communityRepository.save(community);
    }

    @Override
    public void removeMember(String communityName, String userId) {
        Community community = communityRepository.findByName(communityName);
        if (community == null) {
            throw new NotFoundException("Community not found with name: " + communityName);
        }
        community.getSubscriberIds().remove(userId);
        communityRepository.save(community);
    }

    @Override
    public void restrictMember(String communityName, String userId) {
        // Implement logic to restrict a member in the community
    }
}
