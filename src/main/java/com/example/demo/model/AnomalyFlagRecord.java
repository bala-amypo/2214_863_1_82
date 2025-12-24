package com.example.demo.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "anomaly_flag_records")
public class AnomalyFlagRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "employee_id")
    private EmployeeProfile employee;

    @ManyToOne(optional = false)
    @JoinColumn(name = "metric_id")
    private ProductivityMetricRecord metric;

    @Column(nullable = false)
    private String ruleCode;

    @Column(nullable = false)
    private String severity;

    @Column(length = 500)
    private String details;

    @Column(nullable = false)
    private Boolean resolved = false;

    @Column(nullable = false)
    private LocalDateTime flaggedAt;

    // ---------- constructors ----------
    public AnomalyFlagRecord() {
    }

    public AnomalyFlagRecord(EmployeeProfile employee,
                             ProductivityMetricRecord metric,
                             String ruleCode,
                             String severity,
                             String details) {
        this.employee = employee;
        this.metric = metric;
        this.ruleCode = ruleCode;
        this.severity = severity;
        this.details = details;
        this.flaggedAt = LocalDateTime.now();
        this.resolved = false;
    }

    // ---------- getters & setters ----------
    public Long getId() {
        return id;
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
