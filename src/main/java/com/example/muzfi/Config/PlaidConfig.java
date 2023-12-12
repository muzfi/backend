package com.example.muzfi.Config;

import com.plaid.client.ApiClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;

@Configuration
public class PlaidConfig {
    @Bean
    public boolean plaidClient() {
        return ApiClient.Development
                .matches("YOUR_PUBLIC_KEY");
    }

    private ServerHttpSecurity.OAuth2ResourceServerSpec.JwtSpec clientIdAndSecret(String yourClientId, String yourSecret) {
        ServerHttpSecurity.OAuth2ResourceServerSpec.JwtSpec o = null;
        return o;
    }
}
