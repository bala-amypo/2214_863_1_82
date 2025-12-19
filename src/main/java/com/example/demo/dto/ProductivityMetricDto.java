package com.example.demo.dto;

import java.time.LocalDate;

public class ProductivityMetricDto {

    private Long employeeId;
    private Double score;
    private LocalDate recordedDate;

    public ProductivityMetricDto() {}

    public ProductivityMetricDto(Long employeeId, Double score, LocalDate recordedDate) {
        this.employeeId = employeeId;
        this.score = score;
        this.recordedDate = recordedDate;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public LocalDate getRecordedDate() {
        return recordedDate;
    }

    public void setRecordedDate(LocalDate recordedDate) {
        this.recordedDate = recordedDate;
    }
}