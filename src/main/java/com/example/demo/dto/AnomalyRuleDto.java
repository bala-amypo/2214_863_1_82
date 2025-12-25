package com.example.demo.dto;

public class AnomalyRuleDto {

    private String ruleCode;
    private Double thresholdValue;
    private Boolean active;

    public AnomalyRuleDto() {}

    public String getRuleCode() { return ruleCode; }
    public void setRuleCode(String ruleCode) { this.ruleCode = ruleCode; }

    public Double getThresholdValue() { return thresholdValue; }
    public void setThresholdValue(Double thresholdValue) { this.thresholdValue = thresholdValue; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}
