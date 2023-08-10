package com.example.muzfi.Util;

import com.example.muzfi.Dto.OktaProfileAttributesDto;
import com.example.muzfi.Dto.OktaProfileDto;
import com.example.muzfi.Enums.UserRole;
import com.example.muzfi.Services.User.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Component;
import com.example.muzfi.Model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Logger;

@Component
public class OktaLoginSuccessListener implements ApplicationListener<AuthenticationSuccessEvent> {

    private final OktaRestClient oktaRestClient;

    private final UserService userService;

    private final Logger logger = Logger.getLogger(OktaRestClient.class.getName());

    @Autowired
    public OktaLoginSuccessListener(OktaRestClient oktaRestClient, UserService userService) {
        this.oktaRestClient = oktaRestClient;
        this.userService = userService;
    }

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        updateLoggedInUser(event.getAuthentication());
    }

    private void updateLoggedInUser(Authentication authentication) {
        try {

            if (authentication != null && authentication.getPrincipal() instanceof OidcUser oidcUser) {

                String userOktaId = oidcUser.getAttribute("sub");
                ArrayList userGroups = oidcUser.getAttribute("groups");

                ResponseEntity<?> response = oktaRestClient.getOktaUserById(userOktaId);

                if (response.getStatusCode().equals(HttpStatus.OK)) {
                    System.out.println(response.getBody());

                    String jsonResponse = Objects.requireNonNull(response.getBody()).toString();
                    ObjectMapper objectMapper = new ObjectMapper();

                    OktaProfileDto oktaProfile = objectMapper.readValue(jsonResponse, OktaProfileDto.class);
                    OktaProfileAttributesDto oktaProfileAttributes = oktaProfile.getProfile();

                    String firstName = oktaProfileAttributes.getFirstName();
                    String lastName = oktaProfileAttributes.getLastName();
                    String email = oktaProfileAttributes.getEmail();

                    List<UserRole> roles = new ArrayList<>();

                    for (Object group : userGroups) {
                        UserRole userRole = UserRole.valueOf(group.toString());
                        roles.add(userRole);
                    }

                    Optional<User> loggedInUser = userService.getUserByOktaId(userOktaId);

                    if (loggedInUser.isEmpty()) {
                        User newUser = new User();
                        newUser.setOktaId(userOktaId);
                        newUser.setEmail(email);
                        newUser.setFirstName(firstName);
                        newUser.setLastName(lastName);
                        newUser.setRole(roles);

                        userService.createUser(newUser);
                    } else {
                        userService.updateUserRole(loggedInUser.get().getId(), roles);
                    }
                }
            }

        } catch (Exception ex) {
            logger.severe("An error occurred when updating logged in user data - Error Details: " + ex.getMessage());
        }
    }
}
