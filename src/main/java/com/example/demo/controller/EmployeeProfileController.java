package com.example.demo.controller;

import com.example.demo.dto.EmployeeProfileDto;
import com.example.demo.model.EmployeeProfile;
import com.example.demo.service.EmployeeProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/employees")
public class EmployeeProfileController {

    private final EmployeeProfileService employeeProfileService;

    public EmployeeProfileController(EmployeeProfileService employeeProfileService) {
        this.employeeProfileService = employeeProfileService;
    }

    @PostMapping
    public ResponseEntity<EmployeeProfileDto> createEmployee(@RequestBody EmployeeProfileDto employeeDto) {
        EmployeeProfile employee = new EmployeeProfile(employeeDto.getName(), employeeDto.getEmail(), employeeDto.getRole(), employeeDto.getDepartment());
        EmployeeProfile created = employeeProfileService.createEmployee(employee);
        EmployeeProfileDto response = new EmployeeProfileDto(created.getId(), created.getName(), created.getEmail(), created.getRole(), created.getDepartment());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeProfileDto> getEmployee(@PathVariable Long id) {
        EmployeeProfile employee = employeeProfileService.getEmployeeById(id);
        EmployeeProfileDto response = new EmployeeProfileDto(employee.getId(), employee.getName(), employee.getEmail(), employee.getRole(), employee.getDepartment());
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeProfileDto>> getAllEmployees() {
        List<EmployeeProfile> employees = employeeProfileService.getAllEmployees();
        List<EmployeeProfileDto> response = employees.stream()
            .map(e -> new EmployeeProfileDto(e.getId(), e.getName(), e.getEmail(), e.getRole(), e.getDepartment()))
            .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<EmployeeProfileDto> updateEmployeeStatus(@PathVariable Long id, @RequestParam boolean active) {
        EmployeeProfile employee = employeeProfileService.getEmployeeById(id);
        // Status update logic would go here
        EmployeeProfileDto response = new EmployeeProfileDto(employee.getId(), employee.getName(), employee.getEmail(), employee.getRole(), employee.getDepartment());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/lookup/{employeeId}")
    public ResponseEntity<EmployeeProfileDto> lookupEmployee(@PathVariable String employeeId) {
        EmployeeProfile employee = employeeProfileService.findByEmployeeId(employeeId);
        EmployeeProfileDto response = new EmployeeProfileDto(employee.getId(), employee.getName(), employee.getEmail(), employee.getRole(), employee.getDepartment());
        return ResponseEntity.ok(response);
    }
}