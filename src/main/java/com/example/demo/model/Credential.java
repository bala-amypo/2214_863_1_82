package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(
    name = "credentials",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "credential_id")
    }
)
public class Credential {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* ---------- RELATIONSHIP ---------- */

    @ManyToOne(optional = false)
    @JoinColumn(name = "employee_id", nullable = false)
    private EmployeeProfile employee;

    /* ---------- FIELDS ---------- */

    @Column(name = "credential_id", nullable = false, unique = true)
    private String credentialId;

    @Column(nullable = false)
    private String issuer;

    @Column(name = "issued_at", nullable = false)
    private LocalDateTime issuedAt;

    @Column(name = "expires_at")
    private LocalDateTime expiresAt;

    @Column(nullable = false)
    private String status;

    @Column(name = "metadata_json", columnDefinition = "TEXT")
    private String metadataJson;

    /* ---------- CONSTRUCTORS ---------- */

    public Credential() {
        // required by JPA
    }

    public Credential(
            EmployeeProfile employee,
            String credentialId,
            String issuer,
            LocalDateTime issuedAt,
            LocalDateTime expiresAt,
            String status,
            String metadataJson
    ) {
        this.employee = employee;
        this.credentialId = credentialId;
        this.issuer = issuer;
        this.issuedAt = issuedAt;
        this.expiresAt = expiresAt;
        this.status = status;
        this.metadataJson = metadataJson;
    }

    /* ---------- GETTERS & SETTERS ---------- */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EmployeeProfile getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeProfile employee) {
        this.employee = employee;
    }

    public String getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(String credentialId) {
        this.credentialId = credentialId;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public LocalDateTime getIssuedAt() {
        return issuedAt;
    }

    public void setIssuedAt(LocalDateTime issuedAt) {
        this.issuedAt = issuedAt;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMetadataJson() {
        return metadataJson;
    }

    public void setMetadataJson(String metadataJson) {
        this.metadataJson = metadataJson;
    }
}
