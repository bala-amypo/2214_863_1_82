package com.example.demo.dto;

public class TeamSummaryDto {

    private String department;
    private Double averageScore;

    public TeamSummaryDto() {}

    public TeamSummaryDto(String department, Double averageScore) {
        this.department = department;
        this.averageScore = averageScore;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(Double averageScore) {
        this.averageScore = averageScore;
    }
}