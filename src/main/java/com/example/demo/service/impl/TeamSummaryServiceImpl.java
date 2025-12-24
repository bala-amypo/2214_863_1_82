package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.ProductivityMetricRecord;
import com.example.demo.model.TeamSummaryRecord;
import com.example.demo.repository.ProductivityMetricRecordRepository;
import com.example.demo.repository.TeamSummaryRecordRepository;
import com.example.demo.service.TeamSummaryService;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TeamSummaryServiceImpl implements TeamSummaryService {

    private final TeamSummaryRecordRepository teamSummaryRepository;
    private final ProductivityMetricRecordRepository metricRepository;

    // ✅ Constructor injection only
    public TeamSummaryServiceImpl(
            TeamSummaryRecordRepository teamSummaryRepository,
            ProductivityMetricRecordRepository metricRepository
    ) {
        this.teamSummaryRepository = teamSummaryRepository;
        this.metricRepository = metricRepository;
    }

    @Override
    public TeamSummaryRecord generateSummary(String teamName, LocalDate summaryDate) {

        // Enforce one summary per team per date
        teamSummaryRepository
                .findByTeamNameAndSummaryDate(teamName, summaryDate)
                .ifPresent(existing -> {
                    throw new IllegalStateException(
                            "Summary already exists for this team and date"
                    );
                });

        // Fetch all metrics
        List<ProductivityMetricRecord> allMetrics = metricRepository.findAll();

        // Filter metrics by team
        List<ProductivityMetricRecord> teamMetrics = allMetrics.stream()
                .filter(m -> m.getEmployee() != null)
                .filter(m -> teamName.equals(m.getEmployee().getTeamName()))
                .toList();

        if (teamMetrics.isEmpty()) {
            throw new ResourceNotFoundException("Summary not found");
        }

        double avgHours = teamMetrics.stream()
                .mapToDouble(m -> m.getHoursLogged() != null ? m.getHoursLogged() : 0.0)
                .average()
                .orElse(0.0);

        double avgTasks = teamMetrics.stream()
                .mapToInt(m -> m.getTasksCompleted() != null ? m.getTasksCompleted() : 0)
                .average()
                .orElse(0.0);

        double avgScore = teamMetrics.stream()
                .mapToDouble(m -> m.getProductivityScore() != null ? m.getProductivityScore() : 0.0)
                .average()
                .orElse(0.0);

        int anomalyCount = 0; // anomaly count can be derived later if needed

        TeamSummaryRecord summary = new TeamSummaryRecord(
                teamName,
                summaryDate,
                avgHours,
                avgTasks,
                avgScore,
                anomalyCount
        );

        return teamSummaryRepository.save(summary);
    }

    @Override
    public List<TeamSummaryRecord> getSummariesByTeam(String teamName) {
        return teamSummaryRepository.findByTeamName(teamName);
    }

    @Override
    public List<TeamSummaryRecord> getAllSummaries() {
        return teamSummaryRepository.findAll();
    }
}
