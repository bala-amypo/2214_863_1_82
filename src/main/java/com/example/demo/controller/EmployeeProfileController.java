package com.example.demo.controller;

import com.example.demo.model.EmployeeProfile;
import com.example.demo.repository.EmployeeProfileRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeProfileController {

    private final EmployeeProfileRepository employeeRepo;

    public EmployeeProfileController(EmployeeProfileRepository employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    // POST /api/employees
    @PostMapping
    public EmployeeProfile createEmployee(@RequestBody EmployeeProfile employee) {
        return employeeRepo.save(employee);
    }

    // GET /api/employees  ✅ FIXED
    @GetMapping
    public List<EmployeeProfile> getAllEmployees() {
        return employeeRepo.findAll();
    }

    // GET /api/employees/{id}
    @GetMapping("/{id}")
    public EmployeeProfile getEmployeeById(@PathVariable Long id) {
        return employeeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    // GET /api/employees/code/{employeeId}
    @GetMapping("/code/{employeeId}")
    public EmployeeProfile getByEmployeeCode(@PathVariable String employeeId) {
        return employeeRepo.findByEmployeeId(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    // PUT /api/employees/{id}/status?active=true
    @PutMapping("/{id}/status")
    public EmployeeProfile updateStatus(
            @PathVariable Long id,
            @RequestParam boolean active) {

        EmployeeProfile employee = employeeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        employee.setActive(active);
        return employeeRepo.save(employee);
    }
}
