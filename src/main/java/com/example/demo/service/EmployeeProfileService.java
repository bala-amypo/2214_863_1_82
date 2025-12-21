package com.example.demo.service;

import com.example.demo.model.EmployeeProfile;

import java.util.List;

public interface EmployeeService {
    EmployeeProfile create(EmployeeProfile employee);
    EmployeeProfile getById(Long id);
    List<EmployeeProfile> getAll();
}
