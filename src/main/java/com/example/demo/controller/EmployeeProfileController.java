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
    public ResponseEntity<EmployeeProfileDto> createEmployee(
            @RequestBody EmployeeProfileDto dto
    ) {
        EmployeeProfile employee = toEntity(dto);
        EmployeeProfile saved = employeeProfileService.createEmployee(employee);
        return ResponseEntity.ok(toDto(saved));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeProfileDto> getEmployeeById(
            @PathVariable Long id
    ) {
        EmployeeProfile employee = employeeProfileService.getEmployeeById(id);
        return ResponseEntity.ok(toDto(employee));
    }

    @GetMapping
    public ResponseEntity<List<EmployeeProfileDto>> getAllEmployees() {
        List<EmployeeProfileDto> list = employeeProfileService.getAllEmployees()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<EmployeeProfileDto> updateStatus(
            @PathVariable Long id,
            @RequestParam boolean active
    ) {
        EmployeeProfile updated =
                employeeProfileService.updateEmployeeStatus(id, active);
        return ResponseEntity.ok(toDto(updated));
    }

    @GetMapping("/lookup/{employeeId}")
    public ResponseEntity<EmployeeProfileDto> lookupByEmployeeId(
            @PathVariable String employeeId
    ) {
        EmployeeProfile employee = employeeProfileService
                .findByEmployeeId(employeeId)
                .orElseThrow(() ->
                        new RuntimeException("Employee not found"));
        return ResponseEntity.ok(toDto(employee));
    }

    /* ---------- MAPPERS ---------- */

    private EmployeeProfile toEntity(EmployeeProfileDto dto) {
        EmployeeProfile e = new EmployeeProfile();
        e.setEmployeeId(dto.getEmployeeId());
        e.setFullName(dto.getFullName());
        e.setEmail(dto.getEmail());
        e.setTeamName(dto.getTeamName());
        e.setRole(dto.getRole());
        e.setActive(dto.getActive());
        return e;
    }

    private EmployeeProfileDto toDto(EmployeeProfile e) {
        EmployeeProfileDto dto = new EmployeeProfileDto();
        dto.setId(e.getId());
        dto.setEmployeeId(e.getEmployeeId());
        dto.setFullName(e.getFullName());
        dto.setEmail(e.getEmail());
        dto.setTeamName(e.getTeamName());
        dto.setRole(e.getRole());
        dto.setActive(e.getActive());
        dto.setCreatedAt(e.getCreatedAt());
        return dto;
    }
}
