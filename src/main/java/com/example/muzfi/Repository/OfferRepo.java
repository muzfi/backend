package com.ecommerce.shoppingmarket.repo;

import com.ecommerce.shoppingmarket.model.Offer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepo extends MongoRepository<Offer, String > {
    List<Offer> findByUserId(String userId);
}
