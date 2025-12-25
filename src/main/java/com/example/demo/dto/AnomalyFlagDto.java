package com.example.demo.dto;

public class AnomalyFlagDto {

    private Long id;
    private String ruleCode;
    private String severity;
    private Boolean resolved;
    private String details;

    public AnomalyFlagDto() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getRuleCode() { return ruleCode; }
    public void setRuleCode(String ruleCode) { this.ruleCode = ruleCode; }

    public String getSeverity() { return severity; }
    public void setSeverity(String severity) { this.severity = severity; }

    public Boolean getResolved() { return resolved; }
    public void setResolved(Boolean resolved) { this.resolved = resolved; }

    public String getDetails() { return details; }
    public void setDetails(String details) { this.details = details; }
}
