package com.example.muzfi.Services;

import com.example.muzfi.Dto.UserDto.LoginDto;
import com.example.muzfi.Dto.UserDto.PasswordResetDto;
import com.example.muzfi.Dto.UserDto.UserSignupDto;
import com.example.muzfi.Enums.RoleEditAction;
import com.example.muzfi.Enums.UserRole;
import com.example.muzfi.Model.User;
import com.example.muzfi.Repository.UserRepository;
import com.example.muzfi.Services.EmailConfirmationService.EmailConfirmationService;
import com.example.muzfi.Services.User.UserService;
import com.example.muzfi.Util.OktaRestClient;
import jakarta.mail.MessagingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AuthServiceImpl implements AuthService {

    private final OktaRestClient oktaRestClient;

    private final UserService userService;

    private final EmailConfirmationService emailConfirmationService;





    private final Logger log = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Autowired
    public AuthServiceImpl(
            OktaRestClient oktaRestClient,
            UserService userService,
            EmailConfirmationService emailConfirmationService
            ) {

        this.oktaRestClient = oktaRestClient;
        this.userService = userService;
        this.emailConfirmationService = emailConfirmationService;


    }

    @Override
    public User signUp(UserSignupDto userSignupDto) {
        try {
            // Perform user signup logic here
            User newUser = new User();
            newUser.setUserName(userSignupDto.getUsername());
            newUser.setEmail(userSignupDto.getEmail());
            newUser.setPassword(userSignupDto.getPassword());

            // Save the user to the database
            User savedUser = userService.createUser(newUser);

            // Optionally, you can send a confirmation email here if needed
            try {
                emailConfirmationService.sendSignUpConfirmationEmail(savedUser.getEmail(), generateConfirmationToken());
            } catch (MessagingException e) {
                log.error("Error sending confirmation email", e);
            }

            return savedUser;
        } catch (Exception e) {
            // Log an error during signup
            log.error("Error during signup", e);
            // You might choose to throw an exception here if you want to propagate the error
            // For simplicity, this example does not rethrow the exception
            return null;
        }
    }

    private String generateConfirmationToken() {
        // Your logic to generate a confirmation token
        // This is a placeholder; replace it with your actual implementation
        return UUID.randomUUID().toString();
    }



    // Check if received user id owned by logged-in user
    @Override
    public boolean isLoggedInUser(String userId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.isAuthenticated()) {
            OAuth2User user = (OAuth2User) authentication.getPrincipal();

            String loggedUserOktaId = user.getAttribute("sub");
            String requestedUserOktaId = userService.getOktaIdByUserId(userId).get();

            if (loggedUserOktaId.equals(requestedUserOktaId)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }


    // Check if logged-in user is an elite member
    @Override
    public boolean isElite() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.isAuthenticated()) {
            return authentication.getAuthorities().stream()
                    .anyMatch(role -> UserRole.Muzfi_Elite.toString().equals(role.getAuthority()));
        } else {
            return false;
        }
    }

    // Check if logged-in user is an admin
    @Override
    public boolean isAdmin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.isAuthenticated()) {
            return authentication.getAuthorities().stream()
                    .anyMatch(role -> UserRole.Muzfi_Admin.toString().equals(role.getAuthority()));
        } else {
            return false;
        }
    }

    // get logged-in user
    @Override
    public User getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.isAuthenticated()) {
            OAuth2User user = (OAuth2User) authentication.getPrincipal();

            String loggedUserOktaId = user.getAttribute("sub");
            User requestedUser = userService.getUserByOktaId(loggedUserOktaId).get();

            return requestedUser;
        } else {
            return null;
        }
    }

    // Use this method to change user role to "Muzfi_Elite"
    @Override
    public void userRoleToElite(String userOktaId) {
        ResponseEntity<?> response = oktaRestClient.addUserToOktaGroup(userOktaId, UserRole.Muzfi_Elite.toString());

        updateUserRoleInDB(userOktaId, response);

        updateSystemAuthenticationAuthorities(UserRole.Muzfi_Elite, RoleEditAction.ADD);
    }

    // Use this method remove the user role from "Muzfi_Elite"
    @Override
    public void userRoleRemoveElite(String userOktaId) {
        ResponseEntity<?> response = oktaRestClient.removeUserFromOktaGroup(userOktaId, UserRole.Muzfi_Elite.toString());

        updateUserRoleInDB(userOktaId, response);

        updateSystemAuthenticationAuthorities(UserRole.Muzfi_Elite, RoleEditAction.REMOVE);
    }

    // Role updates are added to the database
    private void updateUserRoleInDB(String userOktaId, ResponseEntity<?> response) {
        if (response.getStatusCode().equals(HttpStatus.OK)) {
            ResponseEntity<?> userGroups = oktaRestClient.getOktaGroupsByUserId(userOktaId);

            if (userGroups.getStatusCode().equals(HttpStatus.OK)) {
                List<String> groupNames = oktaRestClient.getGroupNames(Objects.requireNonNull(userGroups.getBody()).toString());

                List<UserRole> roles = new ArrayList<>();
                for (String group : groupNames) {
                    UserRole userRole = UserRole.valueOf(group);
                    roles.add(userRole);
                }

                Optional<User> user = userService.getUserByOktaId(userOktaId);

                user.ifPresent(value -> userService.updateUserRole(value.getId(), roles));
            }
        }
    }

    // UpdateUser Authorities saved in server without performing logout operation
    private void updateSystemAuthenticationAuthorities(UserRole role, RoleEditAction action) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth instanceof OAuth2AuthenticationToken oauthToken) {
            OAuth2AuthenticatedPrincipal currentPrincipal = oauthToken.getPrincipal();

            Map<String, Object> attributes = new HashMap<>(currentPrincipal.getAttributes());
            Set<GrantedAuthority> authorities = new HashSet<>(auth.getAuthorities());

            if (action.equals(RoleEditAction.ADD)) {
                authorities.add(new SimpleGrantedAuthority(role.toString()));
            } else if (action.equals(RoleEditAction.REMOVE)) {
                authorities.remove(new SimpleGrantedAuthority(role.toString()));
            }

            OAuth2User updatedOAuth2User = new DefaultOAuth2User(authorities, attributes, "sub");

            OAuth2AuthenticationToken updatedAuthToken = new OAuth2AuthenticationToken(updatedOAuth2User,
                    authorities, oauthToken.getAuthorizedClientRegistrationId());

            updatedAuthToken.setDetails(auth.getDetails());

            SecurityContextHolder.getContext().setAuthentication(updatedAuthToken);
        }
    }


    @Override
    public void saveResetToken(String email, String token) {
        Optional<User> user = Optional.of(UserRepository.findByEmail(email));
        if (user != null) {
            // Assuming ResetTokenService handles token storage and expiration
            resetTokenService.createResetTokenForUser(user, token);
        } else {
            throw new RuntimeException("User not found with email: " + email); // Consider a more specific exception
        }
    }

    @Override
    public void resetPassword(PasswordResetDto passwordResetDto) {
        // Assuming ResetTokenService can validate the token and find the associated user
        User user = resetTokenService.validateResetToken(passwordResetDto.getToken());
        if (user != null) {
            user.setPassword(passwordEncoder.encode(passwordResetDto.getNewPassword()));
            UserRepository.save(user);
        } else {
            throw new RuntimeException("Invalid or expired reset token."); // Consider a more specific exception
        }
    }
}
