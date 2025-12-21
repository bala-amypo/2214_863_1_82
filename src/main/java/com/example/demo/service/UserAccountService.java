package com.example.demo.service;

import com.example.demo.model.UserAccount;

import java.util.List;

public interface UserService {
    UserAccount register(UserAccount user);
    List<UserAccount> getAll();
}
