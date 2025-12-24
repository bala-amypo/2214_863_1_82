package com.example.demo.controller;

import com.example.demo.dto.CredentialRequestDto;
import com.example.demo.dto.CredentialStatusDto;
import com.example.demo.model.Credential;
import com.example.demo.model.EmployeeProfile;
import com.example.demo.service.CredentialVerificationService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/credentials")
public class CredentialVerificationController {

    private final CredentialVerificationService credentialVerificationService;

    public CredentialVerificationController(
            CredentialVerificationService credentialVerificationService
    ) {
        this.credentialVerificationService = credentialVerificationService;
    }

    @PostMapping
    public ResponseEntity<Credential> registerCredential(
            @RequestBody CredentialRequestDto dto
    ) {
        Credential credential = toEntity(dto);
        Credential saved =
                credentialVerificationService.registerCredential(credential);
        return ResponseEntity.ok(saved);
    }

    @PostMapping("/{credentialId}/verify")
    public ResponseEntity<CredentialStatusDto> verifyCredential(
            @PathVariable String credentialId
    ) {
        CredentialStatusDto status =
                credentialVerificationService.verifyCredential(credentialId);
        return ResponseEntity.ok(status);
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<Credential>> getByEmployee(
            @PathVariable Long employeeId
    ) {
        List<Credential> list =
                credentialVerificationService.getCredentialsForEmployee(employeeId);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{credentialId}")
    public ResponseEntity<Credential> getByCredentialId(
            @PathVariable String credentialId
    ) {
        Credential credential =
                credentialVerificationService.verifyCredential(credentialId) != null
                        ? credentialVerificationService
                                .getCredentialsForEmployee(null)
                                .stream()
                                .filter(c -> credentialId.equals(c.getCredentialId()))
                                .findFirst()
                                .orElseThrow(() ->
                                        new RuntimeException("Credential not found"))
                        : null;

        return ResponseEntity.ok(credential);
    }

    /* ---------- MAPPER ---------- */

    private Credential toEntity(CredentialRequestDto dto) {

        Credential credential = new Credential();
        credential.setCredentialId(dto.getCredentialId());
        credential.setIssuer(dto.getIssuer());
        credential.setIssuedAt(dto.getIssuedAt());
        credential.setExpiresAt(dto.getExpiresAt());
        credential.setMetadataJson(dto.getMetadataJson());

        EmployeeProfile employee = new EmployeeProfile();
        employee.setId(dto.getEmployeeId());
        credential.setEmployee(employee);

        return credential;
    }
}
