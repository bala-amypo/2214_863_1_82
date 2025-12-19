package com.example.demo.dto;

public class CredentialRequestDto {

    private Long employeeId;
    private String type;
    private String value;

    public CredentialRequestDto() {}

    public CredentialRequestDto(Long employeeId, String type, String value) {
        this.employeeId = employeeId;
        this.type = type;
        this.value = value;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}