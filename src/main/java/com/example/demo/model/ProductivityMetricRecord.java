package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "productivity_metrics")
public class ProductivityMetricRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long employeeId;

    @ManyToOne
    @JoinColumn(name = "employee_profile_id", nullable = false)
    @NotNull
    private EmployeeProfile employeeProfile;

    @NotNull
    @Column(nullable = false)
    private Double score;

    @NotNull
    @Column(nullable = false)
    private LocalDate recordedDate;

    private LocalDateTime submittedAt;

    public ProductivityMetricRecord() {}

    public ProductivityMetricRecord(EmployeeProfile employeeProfile, Double score, LocalDate recordedDate) {
        this.employeeProfile = employeeProfile;
        this.score = score;
        this.recordedDate = recordedDate;
        if (employeeProfile != null) {
            this.employeeId = employeeProfile.getId();
        }
    }

    @PrePersist
    protected void onCreate() {
        this.submittedAt = LocalDateTime.now();
        if (this.employeeProfile != null && this.employeeId == null) {
            this.employeeId = this.employeeProfile.getId();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public EmployeeProfile getEmployeeProfile() {
        return employeeProfile;
    }

    public void setEmployeeProfile(EmployeeProfile employeeProfile) {
        this.employeeProfile = employeeProfile;
        if (employeeProfile != null) {
            this.employeeId = employeeProfile.getId();
        }
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public LocalDate getRecordedDate() {
        return recordedDate;
    }

    public void setRecordedDate(LocalDate recordedDate) {
        this.recordedDate = recordedDate;
    }

    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(LocalDateTime submittedAt) {
        this.submittedAt = submittedAt;
    }
}