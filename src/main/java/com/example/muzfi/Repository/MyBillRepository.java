package com.example.muzfi.Repository;

import com.example.muzfi.Model.MyBill;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MyBillRepository extends MongoRepository<MyBill,String> {
}
