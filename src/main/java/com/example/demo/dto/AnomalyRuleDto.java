package com.example.demo.dto;

public class AnomalyRuleDto {

    private String metricName;
    private Double threshold;
    private String comparisonOperator;
    private Boolean active;

    public AnomalyRuleDto() {}

    public AnomalyRuleDto(String metricName, Double threshold, String comparisonOperator, Boolean active) {
        this.metricName = metricName;
        this.threshold = threshold;
        this.comparisonOperator = comparisonOperator;
        this.active = active;
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