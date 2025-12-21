package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Map<String, String> body) {
        // body.get("email"), body.get("password") if needed
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> body) {
        Map<String, Object> response = new HashMap<>();
        response.put("userId", 1L);
        response.put("email", body.get("email"));
        response.put("role", "USER");
        response.put("token", "dummy-token");

        return ResponseEntity.ok(response);
    }
}
