package com.example.muzfi.Repository;

import com.example.muzfi.Model.Wishlist;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WishlistRepository extends MongoRepository<Wishlist, String> {

    List<Wishlist> findAllByUserId(Integer userId);

    Optional<Object> findByUserId(Integer userId);
}
