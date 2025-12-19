package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "credential_verifications")
public class CredentialVerificationEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "credential_id", nullable = false)
    @NotNull
    private Credential credential;

    @NotNull
    @Column(nullable = false)
    private String status;

    private LocalDateTime verifiedAt;

    public CredentialVerificationEvent() {}

    public CredentialVerificationEvent(Credential credential, String status) {
        this.credential = credential;
        this.status = status;
    }

    @PrePersist
    protected void onCreate() {
        this.verifiedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Credential getCredential() {
        return credential;
    }

    public void setCredential(Credential credential) {
        this.credential = credential;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getVerifiedAt() {
        return verifiedAt;
    }

    public void setVerifiedAt(LocalDateTime verifiedAt) {
        this.verifiedAt = verifiedAt;
    }
}