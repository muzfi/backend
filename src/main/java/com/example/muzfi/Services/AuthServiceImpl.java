package com.example.muzfi.Services;

import com.example.muzfi.Model.User;
import com.example.muzfi.enums.RoleEditAction;
import com.example.muzfi.enums.UserRole;
import com.example.muzfi.Util.OktaRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class AuthServiceImpl implements AuthService {

    private final OktaRestClient oktaRestClient;

    private final UserService userService;

    @Autowired
    public AuthServiceImpl(OktaRestClient oktaRestClient, UserService userService) {
        this.oktaRestClient = oktaRestClient;
        this.userService = userService;
    }

    // Use this method to change user role to "Muzfi_Elite"
    @Override
    public void userRoleToElite(String userOktaId) {
        ResponseEntity<?> response = oktaRestClient.addUserToOktaGroup(userOktaId, UserRole.Muzfi_Elite.toString());

        updateUserRoleInDB(userOktaId, response);

        updateAuthenticationAuthorities(UserRole.Muzfi_Elite, RoleEditAction.ADD);
    }

    // Use this method remove the user role from "Muzfi_Elite"
    @Override
    public void userRoleRemoveElite(String userOktaId) {
        ResponseEntity<?> response = oktaRestClient.removeUserFromOktaGroup(userOktaId, UserRole.Muzfi_Elite.toString());

        updateUserRoleInDB(userOktaId, response);

        updateAuthenticationAuthorities(UserRole.Muzfi_Elite, RoleEditAction.REMOVE);
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

    // Update User Authorities without logout operation
    private void updateAuthenticationAuthorities(UserRole role, RoleEditAction action) {
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
}
