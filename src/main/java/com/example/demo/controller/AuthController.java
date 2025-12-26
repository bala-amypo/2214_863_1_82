package com.example.demo.controller;

import com.example.demo.model.UserAccount;
import com.example.demo.repository.UserAccountRepository;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserAccountRepository userAccountRepository;

    public AuthController(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    // -------------------------
    // REGISTER
    // -------------------------
    @PostMapping("/register")
    public UserAccount register(@RequestBody UserAccount user) {
        return userAccountRepository.save(user);
    }

    // -------------------------
    // LOGIN (TOKEN RESPONSE)
    // -------------------------
    @PostMapping("/login")
    public Map<String, String> login(@RequestBody UserAccount request) {

        // simple validation (mock)
        UserAccount user = userAccountRepository
                .findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        // generate simple token (NO JWT)
        String token = user.getUsername() + "-token";

        // return as JSON
        Map<String, String> response = new HashMap<>();
        response.put("token", token);

        return response;
    }
}
