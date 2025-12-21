package com.example.demo.controller;

import com.example.demo.model.EmployeeProfile;
import com.example.demo.service.EmployeeProfileService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeProfileController {
    
    private final EmployeeProfileService employeeProfileService;
    
    public EmployeeProfileController(EmployeeProfileService employeeProfileService) {
        this.employeeProfileService = employeeProfileService;
    }
    
    @PostMapping
    public EmployeeProfile createEmployee(@RequestBody EmployeeProfile employee) {
        return employeeProfileService.createEmployee(employee);
    }
    
    @GetMapping("/{id}")
    public EmployeeProfile getEmployeeById(@PathVariable Long id) {
        return employeeProfileService.getEmployeeById(id);
    }
    
    @GetMapping
    public List<EmployeeProfile> getAllEmployees() {
        return employeeProfileService.getAllEmployees();
    }
    
    @PutMapping("/{id}/status")
    public EmployeeProfile updateEmployeeStatus(@PathVariable Long id, @RequestParam boolean active) {
        return employeeProfileService.updateEmployeeStatus(id, active);
    }
    
    @GetMapping("/lookup/{employeeId}")
    public EmployeeProfile lookupEmployee(@PathVariable String employeeId) {
        return employeeProfileService.findByEmployeeId(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }
}