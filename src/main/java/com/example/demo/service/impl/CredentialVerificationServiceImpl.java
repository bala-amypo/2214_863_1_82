package com.example.demo.service.impl;

import com.example.demo.model.Credential;
import com.example.demo.model.CredentialVerificationEvent;
import com.example.demo.repository.CredentialRepository;
import com.example.demo.repository.CredentialVerificationEventRepository;
import com.example.demo.service.CredentialVerificationService;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CredentialVerificationServiceImpl implements CredentialVerificationService {

    private final CredentialRepository credentialRepository;
    private final CredentialVerificationEventRepository credentialVerificationEventRepository;

    public CredentialVerificationServiceImpl(CredentialRepository credentialRepository,
                                           CredentialVerificationEventRepository credentialVerificationEventRepository) {
        this.credentialRepository = credentialRepository;
        this.credentialVerificationEventRepository = credentialVerificationEventRepository;
    }

    @Override
    public Credential createCredential(Credential credential) {
        return credentialRepository.save(credential);
    }

    @Override
    public CredentialVerificationEvent verifyCredential(Long credentialId, String status) {
        Credential credential = credentialRepository.findById(credentialId)
                .orElseThrow(() -> new ResourceNotFoundException("Credential not found"));
        
        CredentialVerificationEvent event = new CredentialVerificationEvent(credential, status);
        return credentialVerificationEventRepository.save(event);
    }
}