package com.example.demo.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class TeamSummaryRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String teamName;
    private Double avgHoursLogged;
    private Double avgScore;
    private Integer anomalyCount;
    private LocalDate summaryDate;

    public TeamSummaryRecord() {}

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

    public Double getAvgHoursLogged() {
        return avgHoursLogged;
    }

    public void setAvgHoursLogged(Double avgHoursLogged) {
        this.avgHoursLogged = avgHoursLogged;
    }

    public Double getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(Double avgScore) {
        this.avgScore = avgScore;
    }

    public Integer getAnomalyCount() {
        return anomalyCount;
    }

    public void setAnomalyCount(Integer anomalyCount) {
        this.anomalyCount = anomalyCount;
    }

    public LocalDate getSummaryDate() {
        return summaryDate;
    }

    public void setSummaryDate(LocalDate summaryDate) {
        this.summaryDate = summaryDate;
    }
}
