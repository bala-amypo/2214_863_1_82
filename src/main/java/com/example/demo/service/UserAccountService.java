package com.example.demo.service;

import com.example.demo.dto.AuthResponse;
import com.example.demo.model.UserAccount;

public interface UserAccountService {

    UserAccount registerUser(UserAccount user);

    UserAccount findByEmail(String email);

    UserAccount findById(Long id);

    // ✅ ADD THIS
    AuthResponse login(String email, String password);
}
