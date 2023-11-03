package com.example.muzfi.Repository;

import com.example.muzfi.Model.Purchase;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PurchaseRepository extends MongoRepository<Purchase, String> {

}
