package com.example.muzfi.Controller;

import com.example.muzfi.Model.CommunityRule;
import com.example.muzfi.Services.CommunityRuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/community-rules")
@RequiredArgsConstructor
public class CommunityRulesController {
     private final CommunityRuleService communityRuleService;

    @PostMapping
    public ResponseEntity<CommunityRule> createCommunityRule(@RequestBody CommunityRule communityRule) {
        CommunityRule createdCommunityRule = communityRuleService.createCommunityRule(communityRule);
        return new ResponseEntity<>(createdCommunityRule, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommunityRule> updateCommunityRule(@PathVariable String id, @RequestBody CommunityRule communityRule) {
        CommunityRule updatedCommunityRule = communityRuleService.updateCommunityRule(id, communityRule);
        return new ResponseEntity<>(updatedCommunityRule, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommunityRule(@PathVariable String id) {
        communityRuleService.deleteCommunityRule(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/community/{id}")
    public ResponseEntity<List<CommunityRule>> getRulesByCommunityID(@PathVariable String id) {
        List<CommunityRule> communityRules = communityRuleService.getRulesByCommunityID(id);
        return new ResponseEntity<>(communityRules, HttpStatus.OK);
    }
}
