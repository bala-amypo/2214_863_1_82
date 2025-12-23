package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "team_summary")
public class TeamSummaryRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String teamName;

    private LocalDate summaryDate;

    private Double avgHoursLogged;

    private Integer avgTasksCompleted;

    private Integer avgScore;

    private Integer anomalyCount;

    private LocalDateTime generatedAt;

    public TeamSummaryRecord() {}

    // getters & setters (ALL REQUIRED)

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public LocalDate getSummaryDate() {
        return summaryDate;
    }

    public void setSummaryDate(LocalDate summaryDate) {
        this.summaryDate = summaryDate;
    }

    public Double getAvgHoursLogged() {
        return avgHoursLogged;
    }

    public void setAvgHoursLogged(Double avgHoursLogged) {
        this.avgHoursLogged = avgHoursLogged;
    }

    public Integer getAvgTasksCompleted() {
        return avgTasksCompleted;
    }

    public void setAvgTasksCompleted(Integer avgTasksCompleted) {
        this.avgTasksCompleted = avgTasksCompleted;
    }

    public Integer getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(Integer avgScore) {
        this.avgScore = avgScore;
    }

    public Integer getAnomalyCount() {
        return anomalyCount;
    }

    public void setAnomalyCount(Integer anomalyCount) {
        this.anomalyCount = anomalyCount;
    }

    public LocalDateTime getGeneratedAt() {
        return generatedAt;
    }

    public void setGeneratedAt(LocalDateTime generatedAt) {
        this.generatedAt = generatedAt;
    }
}
