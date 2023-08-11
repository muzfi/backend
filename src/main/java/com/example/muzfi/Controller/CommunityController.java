package com.example.muzfi.Controller;

import com.example.muzfi.Dto.CommunityDto;
import com.example.muzfi.Services.CommunityService;
import com.example.muzfi.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/communities")
public class CommunityController {

    private final CommunityService communityService;

    @Autowired
    public CommunityController(CommunityService communityService) {
        this.communityService = communityService;
    }

    @PostMapping
    public ResponseEntity<CommunityDto> createCommunity(@RequestBody CommunityDto communityDto) {
        try {
            CommunityDto createdCommunity = communityService.createCommunity(
                    communityDto.getName(),
                    communityDto.getTitle(),
                    communityDto.getCreatorId(),
                    communityDto.getAbout()
            );
            return new ResponseEntity<>(createdCommunity, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<CommunityDto>> getAllCommunities() {
        try {
            List<CommunityDto> communities = communityService.getAllCommunities();
            return new ResponseEntity<>(communities, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{name}")
    public ResponseEntity<CommunityDto> getCommunityByName(@PathVariable String name) {
        try {
            CommunityDto community = communityService.getCommunityByName(name);
            return new ResponseEntity<>(community, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{communityName}/subscribe/{userId}")
    public ResponseEntity<Void> addUserToCommunity(@PathVariable String communityName, @PathVariable String userId) {
        try {
            communityService.addUserToCommunity(communityName, userId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
