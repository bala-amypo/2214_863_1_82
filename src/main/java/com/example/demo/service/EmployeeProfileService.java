package com.example.demo.service;

import com.example.demo.model.EmployeeProfile;
import java.util.Optional;

public interface EmployeeProfileService {

    EmployeeProfile createEmployee(EmployeeProfile e);
    EmployeeProfile getEmployeeById(Long id);
    Optional<EmployeeProfile> findByEmployeeId(String employeeId);
    EmployeeProfile updateEmployeeStatus(Long id, boolean active);
}
