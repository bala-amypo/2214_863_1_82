package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "credential_verification_events")
public class CredentialVerificationEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* ---------- RELATIONSHIP ---------- */

    @ManyToOne(optional = false)
    @JoinColumn(name = "credential_id", nullable = false)
    private Credential credential;

    /* ---------- FIELDS ---------- */

    @Column(name = "verified_at", nullable = false, updatable = false)
    private LocalDateTime verifiedAt;

    @Column(nullable = false)
    private String result;

    @Column(columnDefinition = "TEXT")
    private String details;

    /* ---------- CONSTRUCTORS ---------- */

    public CredentialVerificationEvent() {
        // required by JPA
    }

    public CredentialVerificationEvent(
            Credential credential,
            String result,
            String details
    ) {
        this.credential = credential;
        this.result = result;
        this.details = details;
    }

    /* ---------- LIFECYCLE ---------- */

    @PrePersist
    protected void onCreate() {
        this.verifiedAt = LocalDateTime.now();
    }

    /* ---------- GETTERS & SETTERS ---------- */

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

    public LocalDateTime getVerifiedAt() {
        return verifiedAt;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
