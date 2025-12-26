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
    private final TeamSummaryRecordRepository summaryRepo;

    public TeamSummaryController(EmployeeProfileRepository employeeRepo,
                                 ProductivityMetricRecordRepository metricRepo,
                                 AnomalyFlagRecordRepository anomalyRepo,
                                 TeamSummaryRecordRepository summaryRepo) {
        this.employeeRepo = employeeRepo;
        this.metricRepo = metricRepo;
        this.anomalyRepo = anomalyRepo;
        this.summaryRepo = summaryRepo;
    }

    // =================================================
    // POST /api/team-summaries/generate?teamName=XXX
    // =================================================
    @PostMapping("/generate")
    public TeamSummaryRecord generate(@RequestParam String teamName) {

        TeamSummaryRecord summary = new TeamSummaryRecord();
        summary.setTeamName(teamName);
        summary.setSummaryDate(LocalDate.now());

        List<EmployeeProfile> employees = employeeRepo.findByTeamName(teamName);

        if (employees.isEmpty()) {
            summary.setAvgHoursLogged(0.0);
            summary.setAvgScore(0.0);
            summary.setAnomalyCount(0);
            return summaryRepo.save(summary);
        }

        List<Long> empIds = employees.stream()
                .map(EmployeeProfile::getId)
                .toList();

        List<ProductivityMetricRecord> metrics =
                metricRepo.findByEmployeeIdIn(empIds);

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

        int anomalyCount = anomalyRepo.findAll().size();
        summary.setAnomalyCount(anomalyCount);

        return summaryRepo.save(summary); // 🔥 SAVE IS IMPORTANT
    }

    // =========================
    // GET /api/team-summaries
    // =========================
    @GetMapping
    public List<TeamSummaryRecord> getAll() {
        return summaryRepo.findAll();
    }

    // =============================
    // GET /api/team-summaries/{id}
    // =============================
    @GetMapping("/{id}")
    public TeamSummaryRecord getById(@PathVariable Long id) {
        return summaryRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Team summary not found"));
    }

    // ==========================================
    // GET /api/team-summaries/team/{teamName}
    // ==========================================
    @GetMapping("/team/{teamName}")
    public List<TeamSummaryRecord> getByTeam(@PathVariable String teamName) {
        return summaryRepo.findByTeamName(teamName);
    }
}
