package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "anomaly_flag_records")
public class AnomalyFlagRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* ---------- RELATIONSHIPS ---------- */

    @ManyToOne(optional = false)
    @JoinColumn(name = "employee_id", nullable = false)
    private EmployeeProfile employee;

    @ManyToOne(optional = false)
    @JoinColumn(name = "metric_id", nullable = false)
    private ProductivityMetricRecord metric;

    /* ---------- FIELDS ---------- */

    @Column(name = "rule_code", nullable = false)
    private String ruleCode;

    @Column(nullable = false)
    private String severity;

    @Column(columnDefinition = "TEXT")
    private String details;

    @Column(name = "flagged_at", nullable = false, updatable = false)
    private LocalDateTime flaggedAt;

    @Column(nullable = false)
    private Boolean resolved;

    /* ---------- CONSTRUCTORS ---------- */

    public AnomalyFlagRecord() {
        // required by JPA
    }

    public AnomalyFlagRecord(
            EmployeeProfile employee,
            ProductivityMetricRecord metric,
            String ruleCode,
            String severity,
            String details
    ) {
        this.employee = employee;
        this.metric = metric;
        this.ruleCode = ruleCode;
        this.severity = severity;
        this.details = details;
    }

    /* ---------- LIFECYCLE ---------- */

    @PrePersist
    protected void onCreate() {
        this.flaggedAt = LocalDateTime.now();
        if (this.resolved == null) {
            this.resolved = false;
        }
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

    public ProductivityMetricRecord getMetric() {
        return metric;
    }

    public void setMetric(ProductivityMetricRecord metric) {
        this.metric = metric;
    }

    public String getRuleCode() {
        return ruleCode;
    }

    public void setRuleCode(String ruleCode) {
        this.ruleCode = ruleCode;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public LocalDateTime getFlaggedAt() {
        return flaggedAt;
    }

    public Boolean getResolved() {
        return resolved;
    }

    public void setResolved(Boolean resolved) {
        this.resolved = resolved;
    }
}
