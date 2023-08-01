package com.example.muzfi.Repository;

import com.example.muzfi.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findUserByOktaId(String oktaId);
}
