package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(
    name = "anomaly_rules",
    uniqueConstraints = @UniqueConstraint(columnNames = "ruleCode")
)
public class AnomalyRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String ruleCode;

    @Column(nullable = false)
    private Boolean active = true;

    public AnomalyRule() {}

    public Long getId() {
        return id;
    }

    public String getRuleCode() {
        return ruleCode;
    }

    public void setRuleCode(String ruleCode) {
        this.ruleCode = ruleCode;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
