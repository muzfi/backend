package com.example.muzfi.Services;

import com.example.muzfi.Model.CommunityRule;
import com.example.muzfi.Repository.CommunityRuleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommunityRuleServiceImpl implements CommunityRuleService {
    private final CommunityRuleRepository communityRuleRepository;


    @Override
    public CommunityRule createCommunityRule(CommunityRule communityRule) {
        return communityRuleRepository.save(communityRule);
    }

    @Override
    public CommunityRule updateCommunityRule(String id, CommunityRule communityRule) {
        CommunityRule existingCommunityRule = communityRuleRepository.findById(id).orElse(null);
        if (existingCommunityRule == null) {
            return null;
        }
        existingCommunityRule.setRule(communityRule.getRule());
        return communityRuleRepository.save(existingCommunityRule);
    }

    @Override
    public void deleteCommunityRule(String id) {
        communityRuleRepository.deleteById(id);
    }

    @Override
    public List<CommunityRule> getRulesByCommunityID(String id) {
        return communityRuleRepository.findByCommunityId(id);
    }
}
