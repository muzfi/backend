package com.example.muzfi.Controller;

import com.example.muzfi.Model.User;
import com.example.muzfi.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        try {
            Optional<List<User>> users = userService.getAllUsers();

            if (users.isPresent()) {
                return new ResponseEntity<>(users, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No Users Available", HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("an unknown error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/okta/{userId}")
    public ResponseEntity<?> getAUserByOktaId(@PathVariable("userId") String userId) {
        try {
            Optional<User> user = userService.getUserByOktaId(userId);

            if (user.isPresent()) {
                return new ResponseEntity<>(user, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No User Available", HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("an unknown error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable("userId") String userId, @RequestBody User user) {
        try {
            User updatedUser = userService.updateUser(userId, user);
            if (updatedUser != null) {
                return new ResponseEntity<>(updatedUser, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No User Found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("an unknown error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
