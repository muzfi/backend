package com.example.muzfi.Services;

import com.example.muzfi.Model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<List<User>> getAllUsers();

    User createUser(User user);

    Optional<User> getUserByOktaId(String oktaId);

    User updateUser(String userId, User user);
}
