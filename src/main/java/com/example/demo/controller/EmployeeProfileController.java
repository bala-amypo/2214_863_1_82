package com.example.demo.controller;

import java.util.Optional;

import org.springframework.web.bind.annotation.*;

import com.example.demo.model.EmployeeProfile;
import com.example.demo.service.EmployeeProfileService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeProfileController {

    private final EmployeeProfileService service;

    public EmployeeProfileController(EmployeeProfileService service) {
        this.service = service;
    }

    @PostMapping
    public EmployeeProfile create(@RequestBody EmployeeProfile employee) {
        return service.createEmployee(employee);
    }

    @GetMapping("/{id}")
    public EmployeeProfile getById(@PathVariable Long id) {
        return service.getEmployeeById(id);
    }

    @GetMapping("/code/{employeeId}")
    public Optional<EmployeeProfile> getByEmployeeId(@PathVariable String employeeId) {
        return service.findByEmployeeId(employeeId);
    }

// ----------- ADDITIONAL ENDPOINTS (Swagger completeness) -----------

@GetMapping
public List<EmployeeProfile> listAllEmployees() {
    return employeeProfileService.getAllEmployees();
}

@PutMapping("/{id}/status")
public EmployeeProfile updateEmployeeStatus(
        @PathVariable Long id,
        @RequestParam boolean active) {
    return employeeProfileService.updateEmployeeStatus(id, active);
}
}