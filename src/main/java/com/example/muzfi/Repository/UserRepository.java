package com.example.muzfi.Repository;

import com.example.muzfi.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    static User findByEmail(String email) {
        return new User();
    }

    Optional<User> findUserByOktaId(String oktaId);

    @Override
    Optional<User> findById(String id);

    List<User> findByUserNameContainingIgnoreCase(String name);

    List<User> findByFirstNameContainingIgnoreCase(String name);

    List<User> findByLastNameContainingIgnoreCase(String name);




}
