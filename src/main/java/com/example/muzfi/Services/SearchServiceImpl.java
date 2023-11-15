package com.example.muzfi.Services;

import com.example.muzfi.Dto.SearchResultDto;
import com.example.muzfi.Enums.SearchType;
import com.example.muzfi.Model.*;
import com.example.muzfi.Model.Post.Listing;
import com.example.muzfi.Model.Post.Poll;
import com.example.muzfi.Model.Post.Topic;
import com.example.muzfi.Model.Thread;
import com.example.muzfi.Repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SearchServiceImpl implements SearchService {

    private final UserRepository userRepository;

    private final TopicRepository topicRepository;

    private final CommunityRepository communityRepository;

    private final GearRepository gearRepository;

    private final ListingRepository listingRepository;

    private final PollRepository pollRepository;

    private final ReviewRepository reviewRepository;

    private final CatalogRepository catalogRepository;

    private final GroupRepository groupRepository;

    private final PageRepository pageRepository;

    private final ThreadRepository threadRepository;

    @Autowired
    public SearchServiceImpl(UserRepository userRepository, TopicRepository topicRepository, CommunityRepository communityRepository, GearRepository gearRepository, ListingRepository listingRepository, PollRepository pollRepository, ReviewRepository reviewRepository, CatalogRepository catalogRepository, GroupRepository groupRepository, PageRepository pageRepository, ThreadRepository threadRepository) {
        this.userRepository = userRepository;
        this.topicRepository = topicRepository;
        this.communityRepository = communityRepository;
        this.gearRepository = gearRepository;
        this.listingRepository = listingRepository;
        this.pollRepository = pollRepository;
        this.reviewRepository = reviewRepository;
        this.catalogRepository = catalogRepository;
        this.groupRepository = groupRepository;
        this.pageRepository = pageRepository;
        this.threadRepository = threadRepository;
    }

    @Override
    public Optional<List<SearchResultDto>> searchUsers(String query) {
        List<User> userNameList = userRepository.findByUserNameContainingIgnoreCase(query);
        List<User> lastNameList = userRepository.findByLastNameContainingIgnoreCase(query);
        List<User> firstNameList = userRepository.findByFirstNameContainingIgnoreCase(query);

        List<SearchResultDto> searchResults = new ArrayList<>();

        if (!userNameList.isEmpty()) {
            for (User user : userNameList) {
                SearchResultDto result = new SearchResultDto();
                result.setId(user.getId());
                result.setSearchedString(user.getUserName() + ": " + user.getFirstName() + " " + user.getLastName());
                result.setSearchType(SearchType.PROFILE);

                searchResults.add(result);
            }
        }

        if (!lastNameList.isEmpty()) {
            for (User user : lastNameList) {
                SearchResultDto result = new SearchResultDto();
                result.setId(user.getId());
                result.setSearchedString(user.getFirstName() + " " + user.getLastName());
                result.setSearchType(SearchType.PROFILE);

                searchResults.add(result);
            }
        }

        if (!firstNameList.isEmpty()) {
            for (User user : firstNameList) {
                SearchResultDto result = new SearchResultDto();
                result.setId(user.getId());
                result.setSearchedString(user.getFirstName() + " " + user.getLastName());
                result.setSearchType(SearchType.PROFILE);

                searchResults.add(result);
            }
        }

        if (searchResults.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(searchResults);
    }


    @Override
    public Optional<List<SearchResultDto>> searchTopics(String query) {
        List<Topic> topicTitleList = topicRepository.findByTitleContainingIgnoreCase(query);

        List<SearchResultDto> searchResults = new ArrayList<>();

        if (!topicTitleList.isEmpty()) {
            for (Topic topic : topicTitleList) {
                SearchResultDto result = new SearchResultDto();
                result.setId(topic.getId());
                result.setSearchedString(topic.getTitle());
                result.setSearchType(SearchType.TOPIC);

                searchResults.add(result);
            }
        }

        if (searchResults.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(searchResults);
    }

    @Override
    public Optional<List<SearchResultDto>> searchCommunities(String query) {
        List<Community> communityNameList = communityRepository.findByNameContainingIgnoreCase(query);
        List<Community> communityTitleList = communityRepository.findByTitleContainingIgnoreCase(query);

        List<SearchResultDto> searchResults = new ArrayList<>();

        if (!communityNameList.isEmpty()) {
            for (Community community : communityNameList) {
                SearchResultDto result = new SearchResultDto();
                result.setId(community.getId());
                result.setSearchedString(community.getName());
                result.setSearchType(SearchType.COMMUNITY);

                searchResults.add(result);
            }
        }

        if (!communityTitleList.isEmpty()) {
            for (Community community : communityTitleList) {
                SearchResultDto result = new SearchResultDto();
                result.setId(community.getId());
                result.setSearchedString(community.getTitle());
                result.setSearchType(SearchType.COMMUNITY);

                searchResults.add(result);
            }
        }

        if (searchResults.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(searchResults);
    }

    @Override
    public Optional<List<SearchResultDto>> searchGears(String query) {
        List<Gear> gearNameList = gearRepository.findByNameContainingIgnoreCase(query);

        //TODO: Search by gear categories

        List<SearchResultDto> searchResults = new ArrayList<>();

        if (!gearNameList.isEmpty()) {
            for (Gear gear : gearNameList) {
                SearchResultDto result = new SearchResultDto();
                result.setId(gear.getId());
                result.setSearchedString(gear.getName());
                result.setSearchType(SearchType.GEAR);

                searchResults.add(result);
            }
        }

        if (searchResults.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(searchResults);
    }

    @Override
    public Optional<List<SearchResultDto>> searchListings(String query) {
        List<Listing> listingTitleList = listingRepository.findByTitleContainingIgnoreCase(query);
        List<Listing> listingSubTitleList = listingRepository.findBySubTitleContainingIgnoreCase(query);

        List<SearchResultDto> searchResults = new ArrayList<>();

        if (!listingTitleList.isEmpty()) {
            for (Listing listing : listingTitleList) {
                SearchResultDto result = new SearchResultDto();
                result.setId(listing.getId());
                result.setSearchedString(listing.getTitle());
                result.setSearchType(SearchType.LISTING);

                searchResults.add(result);
            }
        }

        if (!listingSubTitleList.isEmpty()) {
            for (Listing listing : listingSubTitleList) {
                SearchResultDto result = new SearchResultDto();
                result.setId(listing.getId());
                result.setSearchedString(listing.getSubTitle());
                result.setSearchType(SearchType.LISTING);

                searchResults.add(result);
            }
        }

        if (searchResults.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(searchResults);
    }


    @Override
    public Optional<List<SearchResultDto>> searchPolls(String query) {
        List<Poll> pollTitleList = pollRepository.findByTitleContainingIgnoreCase(query);

        List<SearchResultDto> searchResults = new ArrayList<>();

        if (!pollTitleList.isEmpty()) {
            for (Poll poll : pollTitleList) {
                SearchResultDto result = new SearchResultDto();
                result.setId(poll.getId());
                result.setSearchedString(poll.getTitle());
                result.setSearchType(SearchType.POLL);

                searchResults.add(result);
            }
        }

        if (searchResults.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(searchResults);
    }


    @Override
    public Optional<List<SearchResultDto>> searchReviews(String query) {
        List<Review> reviewTextList = reviewRepository.findByTextContainingIgnoreCase(query);

        List<SearchResultDto> searchResults = new ArrayList<>();

        if (!reviewTextList.isEmpty()) {
            for (Review review : reviewTextList) {
                SearchResultDto result = new SearchResultDto();
                result.setId(review.getId());
                result.setSearchedString(review.getText());
                result.setSearchType(SearchType.REVIEW);

                searchResults.add(result);
            }
        }

        if (searchResults.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(searchResults);
    }


    //TODO: Search inside communities

    //TODO: Search catalogs

    @Override
    public Optional<List<SearchResultDto>> searchCatalogs(String query) {
        List<Catalog> catalogTitleList = catalogRepository.findByTitleContainingIgnoreCase(query);

        List<SearchResultDto> searchResults = new ArrayList<>();

        if (!catalogTitleList.isEmpty()) {
            for (Catalog catalog : catalogTitleList) {
                SearchResultDto result = new SearchResultDto();
                result.setId(catalog.getId());
                result.setSearchedString(catalog.getTitle());
                result.setSearchType(SearchType.CATALOG);

                searchResults.add(result);
            }
        }

        return Optional.of(searchResults);
    }

    @Override
    public Optional<List<SearchResultDto>> searchGroups(String query) {
        List<Group> groupTitleList = groupRepository.findByTitleContainingIgnoreCase(query);

        List<SearchResultDto> searchResults = new ArrayList<>();

        if (!groupTitleList.isEmpty()) {
            for (Group group : groupTitleList) {
                SearchResultDto result = new SearchResultDto();
                result.setId(group.getId());
                result.setSearchedString(group.getTitle());
                result.setSearchType(SearchType.GROUP);

                searchResults.add(result);
            }
        }

        return Optional.of(searchResults);
    }

    @Override
    public Optional<List<SearchResultDto>> searchPages(String query) {
        List<Page> pageTitleList = pageRepository.findByTitleContainingIgnoreCase(query);

        List<SearchResultDto> searchResults = new ArrayList<>();

        if (!pageTitleList.isEmpty()) {
            for (Page page : pageTitleList) {
                SearchResultDto result = new SearchResultDto();
                result.setId(page.getId());
                result.setSearchedString(page.getTitle());
                result.setSearchType(SearchType.PAGE);

                searchResults.add(result);
            }
        }

        return Optional.of(searchResults);
    }

    @Override
    public Optional<List<SearchResultDto>> searchThreads(String query) {
        List<Thread> threadList = threadRepository.findByTitleContainingIgnoreCase(query);

        List<SearchResultDto> searchResults = new ArrayList<>();

        if (!threadList.isEmpty()) {
            for (Thread thread : threadList) {
                SearchResultDto result = new SearchResultDto();
                result.setId(thread.getId());
                result.setSearchedString(thread.getTitle());
                result.setSearchType(SearchType.THREAD);

                searchResults.add(result);
            }
        }

        return Optional.of(searchResults);
    }

    @Override
    public Optional<List<SearchResultDto>> searchGearsByCategories(String query) {
        List<Gear> gearList = gearRepository.findByCategoryContainingIgnoreCase(query);

        List<SearchResultDto> searchResults = new ArrayList<>();

        if (!gearList.isEmpty()) {
            for (Gear gear : gearList) {
                SearchResultDto result = new SearchResultDto();
                result.setId(gear.getId());
                result.setSearchedString(gear.getName());
                result.setSearchType(SearchType.GEAR);

                searchResults.add(result);
            }
        }

        return Optional.of(searchResults);
    }
    //TODO: Search threads


    //TODO: Search by gear categories
}
