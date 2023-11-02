package com.example.muzfi.Repository;

import com.example.muzfi.Model.Earnings;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EarningsRepository extends MongoRepository<Earnings,String> {
    List<Earnings> findByUserId(String userId);

    List<Earnings> findByOrderId(String orderId);
}
