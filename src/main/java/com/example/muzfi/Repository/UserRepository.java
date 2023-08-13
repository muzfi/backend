package com.example.muzfi.Repository;

import com.example.muzfi.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findUserByOktaId(String oktaId);

    @Override
    Optional<User> findById(String id);

}
