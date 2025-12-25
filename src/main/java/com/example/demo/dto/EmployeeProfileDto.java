package com.example.demo.dto;

public class EmployeeProfileDto {

    private String employeeId;
    private String fullName;
    private String email;
    private String teamName;
    private Boolean active;

    public EmployeeProfileDto() {}

    public String getEmployeeId() { return employeeId; }
    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTeamName() { return teamName; }
    public void setTeamName(String teamName) { this.teamName = teamName; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}
