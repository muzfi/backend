package com.example.muzfi.Controller;

import com.example.muzfi.Controller.User.UserController;
import com.example.muzfi.Dto.UserDto.LoginDto;
import com.example.muzfi.Dto.UserDto.UserSignupDto;
import com.example.muzfi.Model.User;
import com.example.muzfi.Services.AuthService;
import com.example.muzfi.Services.EmailConfirmationService.EmailConfirmationService;
import com.example.muzfi.Util.OktaRestClient;
import jakarta.mail.MessagingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);
    private final AuthService authService;
    private final OktaRestClient oktaRestClient;
    private final EmailConfirmationService emailConfirmationService;

    @Autowired
    public AuthController(AuthService authService, OktaRestClient oktaRestClient, EmailConfirmationService emailConfirmationService) {
        this.authService = authService;
        this.oktaRestClient = oktaRestClient;
        this.emailConfirmationService = emailConfirmationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody UserSignupDto userSignupDto) {
        try {
            // Perform user signup logic here using userSignupDto
            User newUser = authService.signUp(userSignupDto);

            // Optionally, send a confirmation email
            sendConfirmationEmail(newUser.getEmail());

            return new ResponseEntity<>("Signup successful", HttpStatus.OK);
        } catch (Exception e) {
            // Log the error
            log.error("Error during signup", e);

            // Optionally, you can throw a custom exception or propagate the error in a different way
            // throw new SignupException("Error during signup", e);

            return new ResponseEntity<>("Error during signup: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto LoginDto) {
        try {
            // Perform user login logic here using userLoginDto
            // Authenticate the user, generate tokens, etc.

            // Optionally, you can customize the welcome message based on user details
            String welcomeMessage = generateWelcomeMessage(LoginDto.getUsername());

            return new ResponseEntity<>(welcomeMessage, HttpStatus.OK);
        } catch (Exception e) {
            // Log the error
            log.error("Error during login", e);

            // Optionally, you can throw a custom exception or propagate the error in a different way
            // throw new LoginException("Error during login", e);

            return new ResponseEntity<>("Error during login: " + e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

    private String generateWelcomeMessage(String username) {
        // Customize the welcome message based on your requirements
        return "Welcome, " + username + "!";
    }

    private void sendConfirmationEmail(String userEmail) {
        try {
            // Generate a confirmation token (replace this with your actual logic)
            String confirmationToken = generateConfirmationToken();

            // Invoke the email service to send the confirmation email
            emailConfirmationService.sendSignUpConfirmationEmail(userEmail, confirmationToken);
        } catch (MessagingException e) {
            // Log the error
            log.error("Error sending confirmation email", e);

            // Optionally, you can throw a custom exception or propagate the error in a different way
            // throw new EmailSendingException("Error sending confirmation email", e);
        }
    }

    private String generateConfirmationToken() {
        // Implement your logic to generate a confirmation token
        // This can be a randomly generated string or a token based on user information
        // For simplicity, you can use a library like UUID.randomUUID().toString()
        return UUID.randomUUID().toString();
    }



    //TODO: Remove in production: For testing purpose
    @PreAuthorize("hasAuthority('Muzfi_Member')")
    @GetMapping("/get-group")
    public ResponseEntity<?> getGroupByName() {
        try {

            ResponseEntity<?> response = oktaRestClient.getOktaGroupByName("Muzfi_Member");

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception ex) {
            return new ResponseEntity<>(ex.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //TODO: Remove in production: For testing purpose
    @PreAuthorize("hasAuthority('Muzfi_Member')")
    @GetMapping("/get-user-groups")
    public ResponseEntity<?> getLoggedUserGroups() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            OAuth2User user = (OAuth2User) authentication.getPrincipal();

            String userOktaId = user.getAttribute("sub");
            ResponseEntity<?> response = oktaRestClient.getOktaGroupsByUserId(userOktaId);

            return response;

        } catch (Exception ex) {
            return new ResponseEntity<>(ex.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //TODO: Remove in production: For testing purpose
    @PreAuthorize("hasAuthority('Muzfi_Member')")
    @GetMapping("/to-elite")
    public ResponseEntity<?> loggedUserToElite() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            OAuth2User user = (OAuth2User) authentication.getPrincipal();

            String userOktaId = user.getAttribute("sub");
            authService.userRoleToElite(userOktaId);

            return new ResponseEntity<>("toElite", HttpStatus.OK);

        } catch (Exception ex) {
            return new ResponseEntity<>("an unknown error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //TODO: Remove in production: For testing purpose
    @PreAuthorize("hasAuthority('Muzfi_Elite')")
    @GetMapping("/remove-elite")
    public ResponseEntity<?> loggedUserRemoveFromElite() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            OAuth2User user = (OAuth2User) authentication.getPrincipal();

            String userOktaId = user.getAttribute("sub");
            authService.userRoleRemoveElite(userOktaId);

            return new ResponseEntity<>("removeElite", HttpStatus.OK);

        } catch (Exception ex) {
            return new ResponseEntity<>("an unknown error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
