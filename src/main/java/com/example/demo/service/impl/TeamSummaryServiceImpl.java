package com.example.demo.service.impl;

import com.example.demo.model.TeamSummaryRecord;
import com.example.demo.model.ProductivityMetricRecord;
import com.example.demo.repository.TeamSummaryRecordRepository;
import com.example.demo.repository.ProductivityMetricRecordRepository;
import com.example.demo.service.TeamSummaryService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TeamSummaryServiceImpl implements TeamSummaryService {

    private final TeamSummaryRecordRepository teamSummaryRecordRepository;
    private final ProductivityMetricRecordRepository productivityMetricRecordRepository;

    public TeamSummaryServiceImpl(TeamSummaryRecordRepository teamSummaryRecordRepository,
                                ProductivityMetricRecordRepository productivityMetricRecordRepository) {
        this.teamSummaryRecordRepository = teamSummaryRecordRepository;
        this.productivityMetricRecordRepository = productivityMetricRecordRepository;
    }

    @Override
    public TeamSummaryRecord generateSummary(String department) {
        List<ProductivityMetricRecord> metrics = productivityMetricRecordRepository.findAll();
        double averageScore = metrics.stream()
                .filter(m -> department.equals(m.getEmployeeProfile().getDepartment()))
                .mapToDouble(ProductivityMetricRecord::getScore)
                .average()
                .orElse(0.0);
        
        TeamSummaryRecord summary = new TeamSummaryRecord(department, averageScore);
        return teamSummaryRecordRepository.save(summary);
    }

    @Override
    public List<TeamSummaryRecord> getAllSummaries() {
        return teamSummaryRecordRepository.findAll();
    }
}