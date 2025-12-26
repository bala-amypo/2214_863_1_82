package com.example.demo.repository;

import com.example.demo.model.EmployeeProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeProfileRepository
        extends JpaRepository<EmployeeProfile, Long> {

    // ✅ REQUIRED by existing code (DO NOT REMOVE)
    Optional<EmployeeProfile> findByEmployeeId(String employeeId);

    // ✅ Used for team summary
    List<EmployeeProfile> findByTeamName(String teamName);
}
