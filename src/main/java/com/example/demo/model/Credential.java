package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "credentials")
public class Credential {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long employeeId;
    private String credentialId;
    private String issuer;
    private LocalDateTime issuedAt;
    private LocalDateTime expiresAt;
    private String status;

    @Column(columnDefinition = "TEXT")
    private String metadataJson;

    public Credential() {
    }

    public Credential(Long employeeId, String credentialId, String issuer) {
        this.employeeId = employeeId;
        this.credentialId = credentialId;
        this.issuer = issuer;
        this.status = "PENDING";
        this.issuedAt = LocalDateTime.now();
    }

    // getters and setters
}
