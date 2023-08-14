package com.example.muzfi.Controller.Post;

import com.example.muzfi.Dto.PostDto.ListingCreateCreateDto;
import com.example.muzfi.Services.AuthService;
import com.example.muzfi.Services.Post.ListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @PreAuthorize("hasAuthority('Muzfi_Member')")
    @PostMapping
    public ResponseEntity<?> createListing(@RequestBody ListingCreateCreateDto listingDto) {
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
}
