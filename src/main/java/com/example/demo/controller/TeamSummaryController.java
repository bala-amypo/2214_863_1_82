package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/team-summaries")
public class TeamSummaryController {

    private final EmployeeProfileRepository employeeRepo;
    private final ProductivityMetricRecordRepository metricRepo;
    private final AnomalyFlagRecordRepository anomalyRepo;

    public TeamSummaryController(EmployeeProfileRepository employeeRepo,
                                 ProductivityMetricRecordRepository metricRepo,
                                 AnomalyFlagRecordRepository anomalyRepo) {
        this.employeeRepo = employeeRepo;
        this.metricRepo = metricRepo;
        this.anomalyRepo = anomalyRepo;
    }

    // =====================================================
    // POST /api/team-summaries/generate?teamName=Engineering
    // =====================================================
    @PostMapping("/generate")
    public TeamSummaryRecord generate(@RequestParam String teamName) {

        TeamSummaryRecord summary = new TeamSummaryRecord();
        summary.setTeamName(teamName);
        summary.setSummaryDate(LocalDate.now());

        // 1️⃣ Find employees in team
        List<EmployeeProfile> employees = employeeRepo.findByTeamName(teamName);

        if (employees.isEmpty()) {
            summary.setAvgHoursLogged(0.0);
            summary.setAvgScore(0.0);
            summary.setAnomalyCount(0);
            return summary;
        }

        // 2️⃣ Collect employee DB IDs
        List<Long> empIds = employees.stream()
                .map(EmployeeProfile::getId)
                .toList();

        // 3️⃣ Fetch productivity metrics
        List<ProductivityMetricRecord> metrics =
                metricRepo.findByEmployeeIdIn(empIds);

        // 4️⃣ Calculate averages
        if (!metrics.isEmpty()) {

            double avgHours = metrics.stream()
                    .mapToDouble(ProductivityMetricRecord::getHoursLogged)
                    .average()
                    .orElse(0.0);

            double avgScore = metrics.stream()
                    .mapToDouble(ProductivityMetricRecord::getProductivityScore)
                    .average()
                    .orElse(0.0);

            summary.setAvgHoursLogged(avgHours);
            summary.setAvgScore(avgScore);

        } else {
            summary.setAvgHoursLogged(0.0);
            summary.setAvgScore(0.0);
        }

        // 5️⃣ Count anomalies for team metrics
        int anomalyCount = (int) anomalyRepo.findAll().stream()
                .filter(a -> a.getMetricId() != null)
                .count();

        summary.setAnomalyCount(anomalyCount);

        return summary;
    }
}
