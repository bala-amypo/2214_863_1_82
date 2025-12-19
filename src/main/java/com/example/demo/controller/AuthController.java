package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.model.UserAccount;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.UserAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserAccountService userAccountService;

    public AuthController(JwtTokenProvider jwtTokenProvider, UserAccountService userAccountService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userAccountService = userAccountService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserAccount> register(@RequestBody AuthRequest authRequest) {
        UserAccount user = new UserAccount(authRequest.getUsername(), authRequest.getPassword(), "USER");
        UserAccount created = userAccountService.createUser(user);
        return ResponseEntity.ok(created);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        try {
            userAccountService.findByUsername(authRequest.getUsername());
            String token = jwtTokenProvider.generateToken(authRequest.getUsername());
            AuthResponse response = new AuthResponse(token);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}