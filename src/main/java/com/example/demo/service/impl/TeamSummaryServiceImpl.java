package com.example.demo.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.TeamSummaryRecord;
import com.example.demo.model.ProductivityMetricRecord;
import com.example.demo.model.AnomalyFlagRecord;
import com.example.demo.repository.TeamSummaryRecordRepository;
import com.example.demo.repository.ProductivityMetricRecordRepository;
import com.example.demo.repository.AnomalyFlagRecordRepository;
import com.example.demo.service.TeamSummaryService;

@Service
@Transactional
public class TeamSummaryServiceImpl implements TeamSummaryService {

    private final TeamSummaryRecordRepository teamSummaryRecordRepository;
    private final ProductivityMetricRecordRepository productivityMetricRecordRepository;
    private final AnomalyFlagRecordRepository anomalyFlagRecordRepository;

    public TeamSummaryServiceImpl(
            TeamSummaryRecordRepository teamSummaryRecordRepository,
            ProductivityMetricRecordRepository productivityMetricRecordRepository,
            AnomalyFlagRecordRepository anomalyFlagRecordRepository) {

        this.teamSummaryRecordRepository = teamSummaryRecordRepository;
        this.productivityMetricRecordRepository = productivityMetricRecordRepository;
        this.anomalyFlagRecordRepository = anomalyFlagRecordRepository;
    }

    @Override
    public TeamSummaryRecord generateSummary(String teamName, LocalDate summaryDate) {

        // 1️⃣ Get all metrics for the given date
        List<ProductivityMetricRecord> metrics =
                productivityMetricRecordRepository.findAll()
                        .stream()
                        .filter(m ->
                                m.getDate().equals(summaryDate))
                        .toList();

        double avgHours = metrics.stream()
                .mapToDouble(ProductivityMetricRecord::getHoursLogged)
                .average()
                .orElse(0.0);

        double avgTasks = metrics.stream()
                .mapToInt(ProductivityMetricRecord::getTasksCompleted)
                .average()
                .orElse(0.0);

        double avgScore = metrics.stream()
                .mapToDouble(ProductivityMetricRecord::getProductivityScore)
                .average()
                .orElse(0.0);

        int anomalyCount = anomalyFlagRecordRepository.findAll().size();

        TeamSummaryRecord summary = new TeamSummaryRecord();
        summary.setTeamName(teamName);
        summary.setSummaryDate(summaryDate);
        summary.setAvgHoursLogged(avgHours);
        summary.setAvgTasksCompleted(avgTasks);
        summary.setAvgScore(avgScore);
        summary.setAnomalyCount(anomalyCount);
        summary.setGeneratedAt(LocalDateTime.now());

        return teamSummaryRecordRepository.save(summary);
    }

    @Override
    public List<TeamSummaryRecord> getSummariesByTeam(String teamName) {
        return teamSummaryRecordRepository.findByTeamName(teamName);
    }

    @Override
    public List<TeamSummaryRecord> getAllSummaries() {
        return teamSummaryRecordRepository.findAll();
    }
}
