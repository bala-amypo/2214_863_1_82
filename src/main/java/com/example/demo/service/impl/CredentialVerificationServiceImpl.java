package com.example.demo.service.impl;

import com.example.demo.dto.CredentialStatusDto;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Credential;
import com.example.demo.model.CredentialVerificationEvent;
import com.example.demo.model.EmployeeProfile;
import com.example.demo.repository.CredentialRepository;
import com.example.demo.repository.CredentialVerificationEventRepository;
import com.example.demo.repository.EmployeeProfileRepository;
import com.example.demo.service.CredentialVerificationService;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CredentialVerificationServiceImpl implements CredentialVerificationService {

    private final CredentialRepository credentialRepository;
    private final CredentialVerificationEventRepository verificationEventRepository;
    private final EmployeeProfileRepository employeeRepository;

    // ✅ Constructor injection only
    public CredentialVerificationServiceImpl(
            CredentialRepository credentialRepository,
            CredentialVerificationEventRepository verificationEventRepository,
            EmployeeProfileRepository employeeRepository
    ) {
        this.credentialRepository = credentialRepository;
        this.verificationEventRepository = verificationEventRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Credential registerCredential(Credential credential) {

        EmployeeProfile employee = employeeRepository.findById(
                credential.getEmployee().getId()
        ).orElseThrow(() ->
                new ResourceNotFoundException("Employee not found"));

        if (!Boolean.TRUE.equals(employee.getActive())) {
            throw new ResourceNotFoundException("Employee not found");
        }

        credential.setEmployee(employee);
        credential.setStatus("PENDING");

        return credentialRepository.save(credential);
    }

    @Override
    public CredentialStatusDto verifyCredential(String credentialId) {

        Credential credential = credentialRepository.findByCredentialId(credentialId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Credential not found"));

        // Check expiry
        if (credential.getExpiresAt() != null &&
                credential.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("Credential expired");
        }

        // Check revoked state
        if ("REVOKED".equalsIgnoreCase(credential.getStatus())) {
            throw new IllegalStateException("Credential revoked");
        }

        credential.setStatus("VERIFIED");
        credentialRepository.save(credential);

        CredentialVerificationEvent event = new CredentialVerificationEvent(
                credential,
                "SUCCESS",
                "Credential verified successfully"
        );
        verificationEventRepository.save(event);

        return new CredentialStatusDto(
                credential.getCredentialId(),
                credential.getStatus(),
                event.getVerifiedAt(),
                event.getDetails()
        );
    }

    @Override
    public List<Credential> getCredentialsForEmployee(Long employeeId) {
        return credentialRepository.findByEmployee_Id(employeeId);
    }
}
