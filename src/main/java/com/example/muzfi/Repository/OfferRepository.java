package com.example.muzfi.Repository;

import com.example.muzfi.Model.Post.Offer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OfferRepository extends MongoRepository<Offer, String> {
    List<Offer> findAllByListingId(String listingId);

    List<Offer> findAllByUserId(String userId);
}
