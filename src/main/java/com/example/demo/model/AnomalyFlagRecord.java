package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "anomaly_flag_records")
public class AnomalyFlagRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ---------- RELATIONSHIPS ----------
    @ManyToOne
    @JoinColumn(name = "employee_id", insertable = false, updatable = false)
    private EmployeeProfile employee;

    @ManyToOne
    @JoinColumn(name = "metric_id", insertable = false, updatable = false)
    private ProductivityMetricRecord metric;

    // ---------- FOREIGN KEYS ----------
    private Long employeeId;
    private Long metricId;

    private String ruleCode;
    private String severity;

    @Column(columnDefinition = "TEXT")
    private String details;

    private LocalDateTime flaggedAt;
    private Boolean resolved;

    // ---------- CONSTRUCTORS ----------

    public AnomalyFlagRecord() {}

    // Constructor REQUIRED by ProductivityMetricService
    public AnomalyFlagRecord(
            EmployeeProfile employee,
            ProductivityMetricRecord metric,
            String ruleCode,
            String severity,
            String details
    ) {
        this.employee = employee;
        this.metric = metric;
        this.employeeId = employee.getId();
        this.metricId = metric.getId();
        this.ruleCode = ruleCode;
        this.severity = severity;
        this.details = details;
        this.flaggedAt = LocalDateTime.now();
        this.resolved = false;
    }

    // ---------- GETTERS & SETTERS ----------

    public Long getId() {
        return id;
    }

    public EmployeeProfile getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeProfile employee) {
        this.employee = employee;
        this.employeeId = employee.getId();
    }

    public ProductivityMetricRecord getMetric() {
        return metric;
    }

    public void setMetric(ProductivityMetricRecord metric) {
        this.metric = metric;
        this.metricId = metric.getId();
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public Long getMetricId() {
        return metricId;
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

    public void setFlaggedAt(LocalDateTime flaggedAt) {
        this.flaggedAt = flaggedAt;
    }

    public Boolean getResolved() {
        return resolved;
    }

    public void setResolved(Boolean resolved) {
        this.resolved = resolved;
    }
}
