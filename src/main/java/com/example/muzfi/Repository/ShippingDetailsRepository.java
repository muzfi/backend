package com.example.muzfi.Repository;

import com.example.muzfi.Model.ShippingDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShippingDetailsRepository extends MongoRepository<ShippingDetails, String> {

}
