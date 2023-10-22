package com.example.muzfi.Controller.Post;

import com.example.muzfi.Dto.PostDto.ListingCreateDto;
import com.example.muzfi.Dto.PostDto.ListingDetailsDto;
import com.example.muzfi.Dto.PostDto.ListingUpdateDto;
import com.example.muzfi.Dto.PostDto.PostDetailsDto;
import com.example.muzfi.Services.AuthService;
import com.example.muzfi.Services.Post.ListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/listings")
public class ListingController {

    private final AuthService authService;

    private final ListingService listingService;

    @Autowired
    public ListingController(AuthService authService, ListingService listingService) {
        this.authService = authService;
        this.listingService = listingService;
    }

    @GetMapping
    public ResponseEntity<?> getAllListings() {
        try {
            Optional<List<ListingDetailsDto>> listingList = listingService.getAllListings();

            if (listingList.isPresent()) {
                return new ResponseEntity<>(listingList.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Cannot retrieve listings", HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("an unknown error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getAllListingsByUserId(@PathVariable("userId") String userId) {
        try {
            Optional<List<ListingDetailsDto>> listingList = listingService.getListingsByUserId(userId);

            if (listingList.isPresent()) {
                return new ResponseEntity<>(listingList.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Cannot retrieve listings", HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("an unknown error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/draft/{userId}")
    public ResponseEntity<?> getAllDraftListingsByUserId(@PathVariable("userId") String userId) {
        try {
            boolean isLoggedInUser = authService.isLoggedInUser(userId);

            if (!isLoggedInUser) {
                return new ResponseEntity<>("Access denied: You are not eligible to retrieve this posts.", HttpStatus.UNAUTHORIZED);
            }

            Optional<List<ListingDetailsDto>> listingList = listingService.getDraftListingsByUserId(userId);

            if (listingList.isPresent()) {
                return new ResponseEntity<>(listingList.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Cannot retrieve draft listings", HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("an unknown error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/{listingId}")
    public ResponseEntity<?> getListingById(@PathVariable("listingId") String listingId) {
        try {
            Optional<ListingDetailsDto> listing = listingService.getListingById(listingId);

            if (listing.isPresent()) {
                return new ResponseEntity<>(listing.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Cannot retrieve listing", HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("an unknown error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAuthority('Muzfi_Member')")
    @PostMapping
    public ResponseEntity<?> createListing(@RequestBody ListingCreateDto listingDto) {
        try {
            String loggedInUserId = listingDto.getAuthorId();

            boolean isLoggedInUser = authService.isLoggedInUser(loggedInUserId);

            if (!isLoggedInUser) {
                return new ResponseEntity<>("Access denied: You are not eligible to perform this action.", HttpStatus.UNAUTHORIZED);
            }

            boolean isElite = authService.isElite();

            if (!isElite) {
                Optional<List<ListingDetailsDto>> currentMonthListingsOpt = listingService.getListingsCreatedByUserInCurrentMonth(loggedInUserId);

                if (currentMonthListingsOpt.isPresent() && currentMonthListingsOpt.get().size() > 5) {
                    return new ResponseEntity<>("Unauthorized: You cannot post more than 5 listings(Including drafts) in a month for your current membership plan. Please upgrade your membership plan to post more.", HttpStatus.UNAUTHORIZED);
                }
            }

            Optional<?> listing = listingService.createListing(listingDto);

            if (listing.isPresent()) {
                return new ResponseEntity<>(listing.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Creat listing failed", HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("an unknown error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAuthority('Muzfi_Member')")
    @PutMapping
    public ResponseEntity<?> updateListing(@RequestBody ListingUpdateDto listingUpdateDto) {
        try {
            Optional<ListingDetailsDto> listingOptional = listingService.getListingById(listingUpdateDto.getId());

            if (listingOptional.isEmpty()) {
                return new ResponseEntity<>("No existing listing found", HttpStatus.NOT_FOUND);
            }

            boolean isLoggedInUser = authService.isLoggedInUser(listingOptional.get().getAuthorId());

            if (!isLoggedInUser) {
                return new ResponseEntity<>("Access denied: You are not eligible to perform this action.", HttpStatus.UNAUTHORIZED);
            }

            Optional<PostDetailsDto> postUpdated = listingService.updateListing(listingUpdateDto);

            if (postUpdated.isPresent()) {
                return new ResponseEntity<>(postUpdated.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Update listing failed", HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("an unknown error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
