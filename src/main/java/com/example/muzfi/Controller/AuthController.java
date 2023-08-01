package com.example.muzfi.Controller;

import com.example.muzfi.Model.User;
import com.example.muzfi.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Optional;

@RestController
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String login(@AuthenticationPrincipal OidcUser user) {
        try {
            String userOktaId = user.getAttribute("sub");

            Optional<User> loggedInUser = userService.getUserByOktaId(userOktaId);

            if (loggedInUser.isEmpty()) {
                User newUser = new User();
                newUser.setOktaId(userOktaId);
                newUser.setEmail(user.getEmail());
                newUser.setFirstName(user.getGivenName());
                newUser.setLastName(user.getFamilyName());
                newUser.setBirthDate(LocalDate.parse(user.getBirthdate()));

                userService.createUser(newUser);
            }

            //TODO: Redirect to web application
            return "Welcome, " + user.getFullName() + "!";

        } catch (Exception ex) {
            return "login error";
        }
    }
}
