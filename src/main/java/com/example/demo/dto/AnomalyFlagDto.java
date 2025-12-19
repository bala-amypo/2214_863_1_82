package com.example.demo.dto;

public class AnomalyFlagDto {

    private Long employeeId;
    private Long ruleId;
    private Double flaggedValue;
    private Boolean resolved;

    public AnomalyFlagDto() {}

    public AnomalyFlagDto(Long employeeId, Long ruleId, Double flaggedValue, Boolean resolved) {
        this.employeeId = employeeId;
        this.ruleId = ruleId;
        this.flaggedValue = flaggedValue;
        this.resolved = resolved;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getRuleId() {
        return ruleId;
    }

    public void setRuleId(Long ruleId) {
        this.ruleId = ruleId;
    }

    public Double getFlaggedValue() {
        return flaggedValue;
    }

    public void setFlaggedValue(Double flaggedValue) {
        this.flaggedValue = flaggedValue;
    }

    public Boolean getResolved() {
        return resolved;
    }

    public void setResolved(Boolean resolved) {
        this.resolved = resolved;
    }
}