package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.EmployeeProfile;
import com.example.demo.repository.EmployeeProfileRepository;
import com.example.demo.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeProfileRepository repository;

    public EmployeeServiceImpl(EmployeeProfileRepository repository) {
        this.repository = repository;
    }

    public EmployeeProfile create(EmployeeProfile employee) {
        return repository.save(employee);
    }

    public EmployeeProfile getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
    }

    public List<EmployeeProfile> getAll() {
        return repository.findAll();
    }
}
