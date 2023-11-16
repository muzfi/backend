package com.example.muzfi.Services;

import com.example.muzfi.Dto.SearchResultDto;

import java.util.List;
import java.util.Optional;

public interface SearchService {
    Optional<List<SearchResultDto>> searchUsers(String query);

    Optional<List<SearchResultDto>> searchTopics(String query);

    Optional<List<SearchResultDto>> searchCommunities(String query);

    Optional<List<SearchResultDto>> searchGears(String query);

    Optional<List<SearchResultDto>> searchListings(String query);

    Optional<List<SearchResultDto>> searchPolls(String query);

    Optional<List<SearchResultDto>> searchReviews(String query);

    Optional<List<SearchResultDto>> searchCatalogs(String query);

    Optional<List<SearchResultDto>> searchGroups(String query);

    Optional<List<SearchResultDto>> searchPages(String query);

    Optional<List<SearchResultDto>> searchThreads(String query);
    Optional<List<SearchResultDto>> searchGearsByCategories(String query);
}
