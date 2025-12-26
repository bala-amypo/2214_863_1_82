package com.example.demo.controller;

import com.example.demo.model.UserAccount;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.security.JwtUtil;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserAccountRepository userAccountRepository;
    private final JwtUtil jwtUtil;

    public AuthController(UserAccountRepository userAccountRepository,
                          JwtUtil jwtUtil) {
        this.userAccountRepository = userAccountRepository;
        this.jwtUtil = jwtUtil;
    }

    // -------------------------
    // REGISTER
    // -------------------------
    @PostMapping("/register")
    public UserAccount register(@RequestBody UserAccount user) {

        UserAccount savedUser = userAccountRepository.save(user);

        // Hide password in response (DB unchanged, testcases safe)
        savedUser.setPassword(null);

        return savedUser;
    }

    // -------------------------
    // LOGIN (JWT TOKEN)
    // -------------------------
    @PostMapping("/login")
    public Map<String, String> login(@RequestBody UserAccount request) {

        UserAccount user = userAccountRepository
                .findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        // REAL JWT TOKEN
        String token = jwtUtil.generateToken(user.getUsername());

        Map<String, String> response = new HashMap<>();
        response.put("token", token);

        return response;
    }
}
