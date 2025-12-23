package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "anomaly_rules")
public class AnomalyRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ruleCode;
    private String description;
    private String thresholdType;
    private Integer thresholdValue;
    private boolean active;

    private String severity; // LOW / MEDIUM / HIGH

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getRuleCode() { return ruleCode; }
    public void setRuleCode(String ruleCode) { this.ruleCode = ruleCode; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getThresholdType() { return thresholdType; }
    public void setThresholdType(String thresholdType) { this.thresholdType = thresholdType; }

    public Integer getThresholdValue() { return thresholdValue; }
    public void setThresholdValue(Integer thresholdValue) { this.thresholdValue = thresholdValue; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    public String getSeverity() { return severity; }
    public void setSeverity(String severity) { this.severity = severity; }
}
