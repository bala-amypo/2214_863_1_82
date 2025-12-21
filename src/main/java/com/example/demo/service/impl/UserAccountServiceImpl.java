package com.example.demo.service.impl;

import com.example.demo.model.UserAccount;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserAccountRepository repository;

    public UserServiceImpl(UserAccountRepository repository) {
        this.repository = repository;
    }

    public UserAccount register(UserAccount user) {
        return repository.save(user);
    }

    public List<UserAccount> getAll() {
        return repository.findAll();
    }
}
