package com.example.muzfi.Services;

import com.example.muzfi.Model.CommunityRule;

import java.util.List;

public interface CommunityRuleService {
    public CommunityRule createCommunityRule(CommunityRule communityRule);
    public CommunityRule updateCommunityRule(String id, CommunityRule communityRule);
    public void deleteCommunityRule(String id);
    public List<CommunityRule> getRulesByCommunityID(String id);

}
