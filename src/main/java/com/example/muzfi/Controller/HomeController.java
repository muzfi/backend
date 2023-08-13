package com.example.muzfi.Controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        try {

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            OAuth2User user = (OAuth2User) authentication.getPrincipal();

            //TODO: Redirect to web application and remove below return string

            return "User Name: " + user.getName() + "<br/>" +
                    "User Authorities: " + user.getAuthorities() + "<br/>" +
                    "Attributes: " + "<br/>" +
                    user.getAttributes();

        } catch (Exception ex) {
            return ex.toString();
        }
    }
}
