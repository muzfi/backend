package com.example.muzfi.Repository;

import com.example.muzfi.Model.OrderDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderDetailsRepository extends MongoRepository<OrderDetails, String> {

}
