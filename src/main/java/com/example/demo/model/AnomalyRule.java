package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "anomaly_rules")
public class AnomalyRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String metricName;

    @NotNull
    @Column(nullable = false)
    private Double threshold;

    @NotNull
    @Column(nullable = false)
    private String comparisonOperator;

    @Column(columnDefinition = "boolean default true")
    private Boolean active = true;

    public AnomalyRule() {}

    public AnomalyRule(String metricName, Double threshold, String comparisonOperator) {
        this.metricName = metricName;
        this.threshold = threshold;
        this.comparisonOperator = comparisonOperator;
        this.active = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMetricName() {
        return metricName;
    }

    public void setMetricName(String metricName) {
        this.metricName = metricName;
    }

    public Double getThreshold() {
        return threshold;
    }

    public void setThreshold(Double threshold) {
        this.threshold = threshold;
    }

    public String getComparisonOperator() {
        return comparisonOperator;
    }

    public void setComparisonOperator(String comparisonOperator) {
        this.comparisonOperator = comparisonOperator;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}