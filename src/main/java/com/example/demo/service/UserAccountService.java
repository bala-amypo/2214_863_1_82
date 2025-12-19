package com.example.demo.service;

import com.example.demo.model.UserAccount;

public interface UserAccountService {
    UserAccount createUser(UserAccount user);
    UserAccount findByUsername(String username);
}