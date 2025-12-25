package com.example.demo.security;

import org.springframework.stereotype.Component;

/**
 * JWT utility class (simplified).
 */
@Component
public class JwtTokenProvider {

    public String generateToken(String username) {
        return "dummy-jwt-token-for-" + username;
    }

    public boolean validateToken(String token) {
        return token != null && token.startsWith("dummy");
    }
}
