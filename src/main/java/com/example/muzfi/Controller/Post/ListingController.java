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
