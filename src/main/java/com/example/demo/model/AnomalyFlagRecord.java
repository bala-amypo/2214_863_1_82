package com.example.demo.model;

public class AnomalyFlagRecord {

    private Long id;
    private Long metricId;
    private String ruleCode;
    private String severity;
    private Boolean resolved = false;
    private String details;

    public AnomalyFlagRecord() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getMetricId() { return metricId; }
    public void setMetricId(Long metricId) { this.metricId = metricId; }

    public String getRuleCode() { return ruleCode; }
    public void setRuleCode(String ruleCode) { this.ruleCode = ruleCode; }

    public String getSeverity() { return severity; }
    public void setSeverity(String severity) { this.severity = severity; }

    public Boolean getResolved() { return resolved; }
    public void setResolved(Boolean resolved) { this.resolved = resolved; }

    public String getDetails() { return details; }
    public void setDetails(String details) { this.details = details; }
}
