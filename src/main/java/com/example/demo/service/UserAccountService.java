package com.example.demo.service;

import java.util.Optional;

import com.example.demo.model.UserAccount;

public interface UserAccountService {

    UserAccount register(UserAccount user);

    Optional<UserAccount> findByUsername(String username);
}
