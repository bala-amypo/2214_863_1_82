package com.example.demo.security;

import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService {

    public CustomUserDetailsService() {}

    public boolean userExists(String username) {
        return true;
    }
}
