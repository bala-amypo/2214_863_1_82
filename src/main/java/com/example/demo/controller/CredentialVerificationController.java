package com.example.demo.controller;

import com.example.demo.dto.CredentialRequestDto;
import com.example.demo.dto.CredentialStatusDto;
import com.example.demo.model.Credential;
import com.example.demo.model.CredentialVerificationEvent;
import com.example.demo.model.EmployeeProfile;
import com.example.demo.service.CredentialVerificationService;
import com.example.demo.service.EmployeeProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/credentials")
public class CredentialVerificationController {

    private final CredentialVerificationService credentialVerificationService;
    private final EmployeeProfileService employeeProfileService;

    public CredentialVerificationController(CredentialVerificationService credentialVerificationService,
                                          EmployeeProfileService employeeProfileService) {
        this.credentialVerificationService = credentialVerificationService;
        this.employeeProfileService = employeeProfileService;
    }

    @PostMapping
    public ResponseEntity<CredentialRequestDto> createCredential(@RequestBody CredentialRequestDto requestDto) {
        EmployeeProfile employee = employeeProfileService.getEmployeeById(requestDto.getEmployeeId());
        Credential credential = new Credential(employee, requestDto.getType(), requestDto.getValue());
        Credential saved = credentialVerificationService.createCredential(credential);
        CredentialRequestDto response = new CredentialRequestDto(saved.getEmployeeProfile().getId(), saved.getType(), saved.getValue());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{credentialId}/verify")
    public ResponseEntity<CredentialStatusDto> verifyCredential(@PathVariable Long credentialId,
                                                               @RequestParam String status) {
        CredentialVerificationEvent event = credentialVerificationService.verifyCredential(credentialId, status);
        CredentialStatusDto response = new CredentialStatusDto(credentialId, status);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<CredentialRequestDto>> getCredentialsByEmployee(@PathVariable Long employeeId) {
        // Get credentials by employee logic would go here
        return ResponseEntity.ok(List.of());
    }

    @GetMapping("/{credentialId}")
    public ResponseEntity<CredentialRequestDto> getCredential(@PathVariable Long credentialId) {
        // Get credential by ID logic would go here
        CredentialRequestDto response = new CredentialRequestDto(1L, "CERTIFICATE", "ABC123");
        return ResponseEntity.ok(response);
    }
}