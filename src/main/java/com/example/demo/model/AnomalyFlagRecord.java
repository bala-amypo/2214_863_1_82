package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "anomaly_flags")
public class AnomalyFlagRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_profile_id", nullable = false)
    @NotNull
    private EmployeeProfile employeeProfile;

    @ManyToOne
    @JoinColumn(name = "anomaly_rule_id", nullable = false)
    @NotNull
    private AnomalyRule anomalyRule;

    @NotNull
    @Column(nullable = false)
    private Double flaggedValue;

    @Column(columnDefinition = "boolean default false")
    private Boolean resolved = false;

    private LocalDateTime flaggedAt;

    public AnomalyFlagRecord() {}

    public AnomalyFlagRecord(EmployeeProfile employeeProfile, AnomalyRule anomalyRule, Double flaggedValue) {
        this.employeeProfile = employeeProfile;
        this.anomalyRule = anomalyRule;
        this.flaggedValue = flaggedValue;
        this.resolved = false;
    }

    @PrePersist
    protected void onCreate() {
        this.flaggedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EmployeeProfile getEmployeeProfile() {
        return employeeProfile;
    }

    public void setEmployeeProfile(EmployeeProfile employeeProfile) {
        this.employeeProfile = employeeProfile;
    }

    public AnomalyRule getAnomalyRule() {
        return anomalyRule;
    }

    public void setAnomalyRule(AnomalyRule anomalyRule) {
        this.anomalyRule = anomalyRule;
    }

    public Double getFlaggedValue() {
        return flaggedValue;
    }

    public void setFlaggedValue(Double flaggedValue) {
        this.flaggedValue = flaggedValue;
    }

    public Boolean getResolved() {
        return resolved;
    }

    public void setResolved(Boolean resolved) {
        this.resolved = resolved;
    }

    public LocalDateTime getFlaggedAt() {
        return flaggedAt;
    }

    public void setFlaggedAt(LocalDateTime flaggedAt) {
        this.flaggedAt = flaggedAt;
    }
}