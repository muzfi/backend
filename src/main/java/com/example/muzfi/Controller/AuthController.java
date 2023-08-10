package com.example.muzfi.Controller;

import com.example.muzfi.Services.AuthService;
import com.example.muzfi.Util.OktaRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final OktaRestClient oktaRestClient;

    @Autowired
    public AuthController(AuthService authService, OktaRestClient oktaRestClient) {
        this.authService = authService;
        this.oktaRestClient = oktaRestClient;
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
            return new ResponseEntity<>(ex.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
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
            return new ResponseEntity<>(ex.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
