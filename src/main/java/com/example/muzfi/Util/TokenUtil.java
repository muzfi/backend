package com.example.muzfi.Util;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TokenUtil {

    public String generateResetToken() {
        return UUID.randomUUID().toString();
    }
}
