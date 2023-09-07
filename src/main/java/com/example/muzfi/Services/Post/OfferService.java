package com.example.muzfi.Services.Post;

import com.example.muzfi.Dto.PostDto.OfferUpdateDto;
import com.example.muzfi.Model.Post.Offer;

import java.util.List;
import java.util.Optional;

public interface OfferService {
    Optional<Offer> createOffer(Offer offer);

    Optional<Offer> getOfferById(String id);

    Optional<List<Offer>> getOffersByListingId(String id);

    Optional<List<Offer>> getOffersByUserId(String userId);

    Optional<Offer> updateOffer(OfferUpdateDto offerDto);

    Optional<String> deleteOffer(String offerId);

    Optional<List<String>> getOfferIdsByListingId(String listingId);

    void deleteOffersByIds(List<String> idList);
}
