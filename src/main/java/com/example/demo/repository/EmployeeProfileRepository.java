package com.example.demo.repository;

import com.example.demo.model.EmployeeProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeProfileRepository
        extends JpaRepository<EmployeeProfile, Long> {

    // 🔹 ALREADY USED BY EXISTING CODE
    Optional<EmployeeProfile> findByEmployeeId(String employeeId);

    // 🔹 REQUIRED FOR TEAM SUMMARY (ADD THIS)
    List<EmployeeProfile> findByTeamName(String teamName);
}
