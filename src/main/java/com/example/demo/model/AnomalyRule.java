package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(
    name = "anomaly_rules",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "rule_code")
    }
)
public class AnomalyRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rule_code", nullable = false, unique = true)
    private String ruleCode;

    @Column(nullable = false)
    private String description;

    @Column(name = "threshold_type", nullable = false)
    private String thresholdType;

    @Column(name = "threshold_value", nullable = false)
    private Double thresholdValue;

    @Column(nullable = false)
    private Boolean active;

    /* ---------- CONSTRUCTORS ---------- */

    public AnomalyRule() {
        // required by JPA
    }

    public AnomalyRule(
            String ruleCode,
            String description,
            String thresholdType,
            Double thresholdValue,
            Boolean active
    ) {
        this.ruleCode = ruleCode;
        this.description = description;
        this.thresholdType = thresholdType;
        this.thresholdValue = thresholdValue;
        this.active = active;
    }

    /* ---------- GETTERS & SETTERS ---------- */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRuleCode() {
        return ruleCode;
    }

    public void setRuleCode(String ruleCode) {
        this.ruleCode = ruleCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThresholdType() {
        return thresholdType;
    }

    public void setThresholdType(String thresholdType) {
        this.thresholdType = thresholdType;
    }

    public Double getThresholdValue() {
        return thresholdValue;
    }

    public void setThresholdValue(Double thresholdValue) {
        this.thresholdValue = thresholdValue;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
