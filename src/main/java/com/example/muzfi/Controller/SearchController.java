package com.example.muzfi.Controller;

import com.example.muzfi.Dto.SearchResultDto;
import com.example.muzfi.Services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/search")
public class SearchController {

    private final SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/profiles")
    public ResponseEntity<?> searchProfiles(@RequestParam("query") String query) {
        try {
            Optional<List<SearchResultDto>> searchResults = searchService.searchUsers(query);

            if (searchResults.isPresent()) {
                return new ResponseEntity<>(searchResults.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No search results found", HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("An unknown error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/topics")
    public ResponseEntity<?> searchTopics(@RequestParam("query") String query) {
        try {
            Optional<List<SearchResultDto>> searchResults = searchService.searchTopics(query);

            if (searchResults.isPresent()) {
                return new ResponseEntity<>(searchResults.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No search results found", HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("An unknown error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/communities")
    public ResponseEntity<?> searchCommunities(@RequestParam("query") String query) {
        try {
            Optional<List<SearchResultDto>> searchResults = searchService.searchCommunities(query);

            if (searchResults.isPresent()) {
                return new ResponseEntity<>(searchResults.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No search results found", HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("An unknown error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/gears")
    public ResponseEntity<?> searchGears(@RequestParam("query") String query) {
        try {
            Optional<List<SearchResultDto>> searchResults = searchService.searchGears(query);

            if (searchResults.isPresent()) {
                return new ResponseEntity<>(searchResults.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No search results found", HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("An unknown error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listings")
    public ResponseEntity<?> searchListings(@RequestParam("query") String query) {
        try {
            Optional<List<SearchResultDto>> searchResults = searchService.searchListings(query);

            if (searchResults.isPresent()) {
                return new ResponseEntity<>(searchResults.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No search results found", HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("An unknown error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/polls")
    public ResponseEntity<?> searchPolls(@RequestParam("query") String query) {
        try {
            Optional<List<SearchResultDto>> searchResults = searchService.searchPolls(query);

            if (searchResults.isPresent()) {
                return new ResponseEntity<>(searchResults.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No search results found", HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("An unknown error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/reviews")
    public ResponseEntity<?> searchReviews(@RequestParam("query") String query) {
        try {
            Optional<List<SearchResultDto>> searchResults = searchService.searchReviews(query);

            if (searchResults.isPresent()) {
                return new ResponseEntity<>(searchResults.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No search results found", HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("An unknown error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/catalogs")
    public ResponseEntity<?> searchCatalogs(@RequestParam("query") String query) {
        try {
            Optional<List<SearchResultDto>> searchResults = searchService.searchCatalogs(query);

            if (searchResults.isPresent()) {
                return new ResponseEntity<>(searchResults.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No search results found", HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("An unknown error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/threads")
    public ResponseEntity<?> searchThreads(@RequestParam("query") String query) {
        try {
            Optional<List<SearchResultDto>> searchResults = searchService.searchThreads(query);

            if (searchResults.isPresent()) {
                return new ResponseEntity<>(searchResults.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No search results found", HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("An unknown error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/groups")
    public ResponseEntity<?> searchGroups(@RequestParam("query") String query) {
        try {
            Optional<List<SearchResultDto>> searchResults = searchService.searchGroups(query);

            if (searchResults.isPresent()) {
                return new ResponseEntity<>(searchResults.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No search results found", HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("An unknown error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/pages")
    public ResponseEntity<?> searchPages(@RequestParam("query") String query) {
        try {
            Optional<List<SearchResultDto>> searchResults = searchService.searchPages(query);

            if (searchResults.isPresent()) {
                return new ResponseEntity<>(searchResults.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No search results found", HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("An unknown error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/gears/categories")
    public ResponseEntity<?> searchGearsByCategories(@RequestParam("query") String query) {
        try {
            Optional<List<SearchResultDto>> searchResults = searchService.searchGearsByCategories(query);

            if (searchResults.isPresent()) {
                return new ResponseEntity<>(searchResults.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No search results found", HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("An unknown error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
