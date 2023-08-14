package com.example.muzfi.Services.Post;

import com.example.muzfi.Dto.PostDto.ListingCreateCreateDto;

import java.util.Optional;

public interface ListingService {
    Optional<?> createListing(ListingCreateCreateDto listingDto);
}
