package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "credential_verification_events")
public class CredentialVerificationEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long credentialId;
    private LocalDateTime verifiedAt;
    private String result;
    private String details;

    public CredentialVerificationEvent() {
    }

    public CredentialVerificationEvent(Long credentialId, String result, String details) {
        this.credentialId = credentialId;
        this.result = result;
        this.details = details;
        this.verifiedAt = LocalDateTime.now();
    }

    // getters and setters
}
