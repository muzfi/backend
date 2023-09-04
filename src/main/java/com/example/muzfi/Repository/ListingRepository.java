package com.example.muzfi.Repository;

import com.example.muzfi.Model.Post.Listing;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ListingRepository extends MongoRepository<Listing, String> {
    List<Listing> findByTitleContainingIgnoreCase(String name);

    List<Listing> findBySubTitleContainingIgnoreCase(String name);

    List<Listing> findAllByAuthorId(String userId);
}
