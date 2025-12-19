package com.example.demo.service;

import com.example.demo.model.Credential;
import com.example.demo.model.CredentialVerificationEvent;

public interface CredentialVerificationService {
    Credential createCredential(Credential credential);
    CredentialVerificationEvent verifyCredential(Long credentialId, String status);
}