package com.example.demo.service;

import com.example.demo.model.UserAccount;
import java.util.List;

public interface UserAccountService {

    UserAccount registerUser(UserAccount user);

    List<UserAccount> getAllUsers();
}
