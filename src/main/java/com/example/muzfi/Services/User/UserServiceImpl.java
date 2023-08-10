package com.example.muzfi.Services.User;

import com.example.muzfi.Model.User;
import com.example.muzfi.Enums.UserRole;
import com.example.muzfi.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<List<User>> getAllUsers() {
        return Optional.of(userRepository.findAll());
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserByOktaId(String oktaId) {
        return Optional.ofNullable(userRepository.findUserByOktaId(oktaId));
    }

    @Override
    public User updateUser(String userId, User user) {
        User existingUser = userRepository.findById(userId).orElse(null);
        if (existingUser != null) {
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            existingUser.setLocation(user.getLocation());
            existingUser.setBirthDate(user.getBirthDate());
            existingUser.setDescription(user.getDescription());

            return userRepository.save(existingUser);
        }
        return null;
    }

    @Override
    public User updateUserRole(String userId, List<UserRole> userRoles) {
        User existingUser = userRepository.findById(userId).orElse(null);
        if (existingUser != null) {
            existingUser.setRole(userRoles);
            return userRepository.save(existingUser);
        }
        return null;
    }
}
