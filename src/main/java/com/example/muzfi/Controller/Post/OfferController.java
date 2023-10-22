package com.example.muzfi.Controller.Post;

import com.example.muzfi.Dto.PostDto.ListingDetailsDto;
import com.example.muzfi.Dto.PostDto.OfferUpdateDto;
import com.example.muzfi.Model.Post.Offer;
import com.example.muzfi.Services.AuthService;
import com.example.muzfi.Services.Post.ListingService;
import com.example.muzfi.Services.Post.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/offers")
public class OfferController {

    private final OfferService offerService;

    private final AuthService authService;

    private final ListingService listingService;

    @Autowired
    public OfferController(OfferService offerService, AuthService authService, ListingService listingService) {
        this.offerService = offerService;
        this.authService = authService;
        this.listingService = listingService;
    }


    @PreAuthorize("hasAuthority('Muzfi_Member')")
    @PostMapping
    public ResponseEntity<?> createOffer(@RequestBody Offer offer) {
        try {
            String loggedInUserId = offer.getUserId();

            boolean isLoggedInUser = authService.isLoggedInUser(loggedInUserId);

            if (!isLoggedInUser) {
                return new ResponseEntity<>("Access denied: You are not eligible to perform this action.", HttpStatus.UNAUTHORIZED);
            }

            Optional<ListingDetailsDto> listingOptional = listingService.getListingById(offer.getListingId());

            if (listingOptional.isEmpty()) {
                return new ResponseEntity<>("Listing not found", HttpStatus.NOT_FOUND);
            }

            Optional<?> offerOpt = offerService.createOffer(offer);

            if (offerOpt.isPresent()) {
                return new ResponseEntity<>(offerOpt.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Creat offer failed", HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("an unknown error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PreAuthorize("hasAuthority('Muzfi_Member')")
    @GetMapping("/by-listing/{listingId}")
    public ResponseEntity<?> getOffersByListing(@PathVariable("listingId") String listingId, @RequestParam("userId") String userId) {
        try {
            boolean isLoggedInUser = authService.isLoggedInUser(userId);

            Optional<ListingDetailsDto> listingOptional = listingService.getListingById(listingId);

            if (!isLoggedInUser || (listingOptional.isPresent() && !listingOptional.get().getAuthorId().equals(userId))) {
                return new ResponseEntity<>("Access denied: You are not eligible to perform this action.", HttpStatus.UNAUTHORIZED);
            }

            Optional<?> offerOpt = offerService.getOffersByListingId(listingId);

            if (offerOpt.isPresent()) {
                return new ResponseEntity<>(offerOpt.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Retrieving offers failed", HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("an unknown error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PreAuthorize("hasAuthority('Muzfi_Member')")
    @GetMapping("/by-user/{userId}")
    public ResponseEntity<?> getOffersByUser(@PathVariable("userId") String userId) {
        try {
            boolean isLoggedInUser = authService.isLoggedInUser(userId);

            if (!isLoggedInUser) {
                return new ResponseEntity<>("Access denied: You are not eligible to perform this action.", HttpStatus.UNAUTHORIZED);
            }

            Optional<?> offerOpt = offerService.getOffersByUserId(userId);

            if (offerOpt.isPresent()) {
                return new ResponseEntity<>(offerOpt.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Retrieving offers failed", HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("an unknown error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PreAuthorize("hasAuthority('Muzfi_Member')")
    @PutMapping
    public ResponseEntity<?> updateOffer(@RequestBody OfferUpdateDto offerDto) {
        try {
            boolean isLoggedInUser = authService.isLoggedInUser(offerDto.getUserId());

            if (!isLoggedInUser) {
                return new ResponseEntity<>("Access denied: You are not eligible to perform this action.", HttpStatus.UNAUTHORIZED);
            }

            Optional<?> offerOpt = offerService.updateOffer(offerDto);

            if (offerOpt.isPresent()) {
                return new ResponseEntity<>(offerOpt.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Updating offer failed", HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("an unknown error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAuthority('Muzfi_Member')")
    @DeleteMapping("/{offerId}")
    public ResponseEntity<?> deleteOffer(@PathVariable("offerId") String offerId, @RequestParam("userId") String userId) {
        try {
            boolean isLoggedInUser = authService.isLoggedInUser(userId);

            if (!isLoggedInUser) {
                return new ResponseEntity<>("Access denied: You are not eligible to perform this action.", HttpStatus.UNAUTHORIZED);
            }

            Optional<?> offerOpt = offerService.deleteOffer(offerId);

            if (offerOpt.isPresent()) {
                return new ResponseEntity<>(offerOpt.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Deleting offer failed", HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("an unknown error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
