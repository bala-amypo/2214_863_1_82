package com.example.demo.controller;

import com.example.demo.model.UserAccount;
import com.example.demo.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserAccountRepository userAccountRepository;

    // -----------------------------
    // REGISTER
    // -----------------------------
    @PostMapping("/register")
    public UserAccount register(@RequestBody UserAccount user) {
        return userAccountRepository.save(user);
    }

    // -----------------------------
    // LOGIN  ✅ FIXED
    // -----------------------------
    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> payload) {

        String username = payload.get("username");
        String password = payload.get("password");

        if (username == null || password == null) {
            return "Invalid credentials";
        }

        return userAccountRepository
                .findByUsername(username)
                .filter(u -> u.getPassword().equals(password))
                .map(u -> "Login successful")
                .orElse("Invalid credentials");
    }
}
