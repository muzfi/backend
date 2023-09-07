package com.example.muzfi.Repository;

import com.example.muzfi.Model.Post.Listing;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface ListingRepository extends MongoRepository<Listing, String> {
    List<Listing> findByTitleContainingIgnoreCase(String name);

    List<Listing> findBySubTitleContainingIgnoreCase(String name);

    List<Listing> findAllByAuthorId(String userId);

    @Query("{ 'authorId' : ?0, 'createdDateTime' : { $gte : ?1, $lt : ?2 } }")
    List<Listing> findListingsCreatedByUserInCurrentMonth(String userId, LocalDateTime startOfMonth, LocalDateTime startOfNextMonth);

}
