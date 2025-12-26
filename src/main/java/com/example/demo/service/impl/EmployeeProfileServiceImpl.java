package com.example.demo.service.impl;

import com.example.demo.model.EmployeeProfile;
import com.example.demo.repository.EmployeeProfileRepository;
import com.example.demo.service.EmployeeProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeProfileServiceImpl implements EmployeeProfileService {

    @Autowired
    private EmployeeProfileRepository employeeProfileRepository;

    @Override
    public EmployeeProfile createEmployee(EmployeeProfile employee) {
        return employeeProfileRepository.save(employee);
    }

    @Override
    public EmployeeProfile getEmployeeById(Long id) {
        return employeeProfileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    @Override
    public Optional<EmployeeProfile> findByEmployeeId(String employeeId) {
        return employeeProfileRepository.findByEmployeeId(employeeId);
    }

    // ✅ DO NOT USE @Override (interface doesn't declare it)
    public List<EmployeeProfile> getAllEmployees() {
        return employeeProfileRepository.findAll();
    }

    @Override
    public EmployeeProfile updateEmployeeStatus(Long id, boolean active) {
        EmployeeProfile employee = getEmployeeById(id);
        employee.setActive(active);
        return employeeProfileRepository.save(employee);
    }
}
