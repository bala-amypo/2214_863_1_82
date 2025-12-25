package com.example.demo.security;

import org.springframework.stereotype.Service;

/**
 * Dummy user details service for authentication simulation.
 */
@Service
public class CustomUserDetailsService {

    public CustomUserDetailsService() {}

    public boolean userExists(String username) {
        return true;
    }
}
