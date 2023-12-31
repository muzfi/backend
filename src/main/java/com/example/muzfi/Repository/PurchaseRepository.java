package com.example.muzfi.Repository;

import com.example.muzfi.Model.Purchase;
import com.example.muzfi.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PurchaseRepository extends MongoRepository<Purchase, String> {

    List<Purchase> findByUser(User user);

    void deleteByUser(User user1);
}
