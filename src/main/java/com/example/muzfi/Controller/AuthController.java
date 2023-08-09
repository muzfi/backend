package com.example.muzfi.Controller;

import com.example.muzfi.Services.UserService;
import com.example.muzfi.Util.OktaRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final OktaRestClient oktaRestClient;

    @Autowired
    public AuthController(UserService userService, OktaRestClient oktaRestClient) {
        this.userService = userService;
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
    public ResponseEntity<?> getLoggedUserGroups(@AuthenticationPrincipal OidcUser user) {
        try {
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
    public ResponseEntity<?> loggedUserToElite(@AuthenticationPrincipal OidcUser user) {
        try {
            String userOktaId = user.getAttribute("sub");
            oktaRestClient.addUserToOktaGroup(userOktaId, "Muzfi_Elite");

            return new ResponseEntity<>("toElite", HttpStatus.OK);

        } catch (Exception ex) {
            return new ResponseEntity<>(ex.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //TODO: Remove in production: For testing purpose
    @PreAuthorize("hasAuthority('Muzfi_Elite')")
    @GetMapping("/remove-elite")
    public ResponseEntity<?> loggedUserRemoveFromElite(@AuthenticationPrincipal OidcUser user) {
        try {
            String userOktaId = user.getAttribute("sub");
            oktaRestClient.removeUserFromOktaGroup(userOktaId, "Muzfi_Elite");

            return new ResponseEntity<>("removeElite", HttpStatus.OK);

        } catch (Exception ex) {
            return new ResponseEntity<>(ex.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
