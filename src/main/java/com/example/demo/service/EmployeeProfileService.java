package com.example.demo.service;

import com.example.demo.model.EmployeeProfile;
import java.util.List;

public interface EmployeeProfileService {
    EmployeeProfile createEmployee(EmployeeProfile employeeProfile);
    EmployeeProfile getEmployeeById(Long id);
    List<EmployeeProfile> getAllEmployees();
    EmployeeProfile findByEmployeeId(String employeeId);
}