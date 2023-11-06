package com.example.muzfi.Controller;

import com.example.muzfi.Dto.CommunityDto;
import com.example.muzfi.Model.Community;
import com.example.muzfi.Model.CommunitySettingsUpdate;
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

    @Autowired
    private final CommunityService communityService;

    @Autowired
    public CommunityController(CommunityService communityService) {
        this.communityService = communityService;
    }

    @PostMapping("/create")
    public ResponseEntity<CommunityDto> createCommunity(@RequestBody CommunityDto communityDto) {
        try {
            CommunityDto createdCommunity = communityService.createCommunity(communityDto);
            return new ResponseEntity<>(createdCommunity, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/allcommunity")
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

    @GetMapping("/{communityName}/similar")
    public ResponseEntity<List<CommunityDto>> getSimilarCommunities(@PathVariable String communityName) {
        try {
            List<CommunityDto> similarCommunities = communityService.getSimilarCommunities(communityName);
            return new ResponseEntity<>(similarCommunities, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // New Methods:

    @PostMapping("/{communityName}/addModerator/{userId}")
    public ResponseEntity<Void> addModerator(@PathVariable String communityName, @PathVariable String userId) {
        try {
            communityService.addModerator(communityName, userId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{communityName}/removeModerator/{userId}")
    public ResponseEntity<Void> removeModerator(@PathVariable String communityName, @PathVariable String userId) {
        try {
            communityService.removeModerator(communityName, userId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{communityName}/addMember/{userId}")
    public ResponseEntity<Void> addMember(@PathVariable String communityName, @PathVariable String userId) {
        try {
            communityService.addMember(communityName, userId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{communityName}/removeMember/{userId}")
    public ResponseEntity<Void> removeMember(@PathVariable String communityName, @PathVariable String userId) {
        try {
            communityService.removeMember(communityName, userId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{communityName}/restrictMember/{userId}")
    public ResponseEntity<Void> restrictMember(@PathVariable String communityName, @PathVariable String userId) {
        try {
            communityService.restrictMember(communityName, userId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateCommunity/{id}")
    public ResponseEntity<Community> updateCommunity(@PathVariable String id, @RequestBody CommunitySettingsUpdate communitySettingsUpdate) {
        Community updatedCommunity = communityService.updateCommunity(id, communitySettingsUpdate);
        return new ResponseEntity<>(updatedCommunity, HttpStatus.OK);
    }

    @DeleteMapping("/deleteCommunity/{id}")
    public ResponseEntity<Void> deleteCommunity(@PathVariable String id) {
        communityService.deleteCommunity(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
