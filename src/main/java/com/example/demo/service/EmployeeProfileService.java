package com.example.demo.service;

import java.util.Optional;
import java.util.List;
import com.example.demo.model.EmployeeProfile;

public interface EmployeeProfileService {

    EmployeeProfile createEmployee(EmployeeProfile employee);

    EmployeeProfile getEmployeeById(Long id);

    Optional<EmployeeProfile> findByEmployeeId(String employeeId);

    EmployeeProfile updateEmployeeStatus(Long id, boolean active);

    List<EmployeeProfile> getAllEmployees();
}
