package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "team_summaries")
public class TeamSummaryRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String teamName;
    private LocalDate summaryDate;
    private double averageProductivity;
    private long anomalyCount;

    public TeamSummaryRecord() {}

    public Long getId() {
        return id;
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

    public double getAverageProductivity() {
        return averageProductivity;
    }

    public void setAverageProductivity(double averageProductivity) {
        this.averageProductivity = averageProductivity;
    }

    public long getAnomalyCount() {
        return anomalyCount;
    }

    public void setAnomalyCount(long anomalyCount) {
        this.anomalyCount = anomalyCount;
    }
}
