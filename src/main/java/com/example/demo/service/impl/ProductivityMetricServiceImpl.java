package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.ProductivityMetricService;
import com.example.demo.util.ProductivityCalculator;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductivityMetricServiceImpl implements ProductivityMetricService {

    private final ProductivityMetricRecordRepository metricRepository;
    private final EmployeeProfileRepository employeeRepository;
    private final AnomalyRuleRepository anomalyRuleRepository;
    private final AnomalyFlagRecordRepository anomalyFlagRepository;

    // ⚠️ Constructor parameter ORDER matters (tests reflect this)
    public ProductivityMetricServiceImpl(
            ProductivityMetricRecordRepository metricRepository,
            EmployeeProfileRepository employeeRepository,
            AnomalyRuleRepository anomalyRuleRepository,
            AnomalyFlagRecordRepository anomalyFlagRepository
    ) {
        this.metricRepository = metricRepository;
        this.employeeRepository = employeeRepository;
        this.anomalyRuleRepository = anomalyRuleRepository;
        this.anomalyFlagRepository = anomalyFlagRepository;
    }

    @Override
    public ProductivityMetricRecord recordMetric(ProductivityMetricRecord metric) {

        // 1️⃣ Validate employee
        EmployeeProfile employee = employeeRepository.findById(
                metric.getEmployee().getId()
        ).orElseThrow(() ->
                new ResourceNotFoundException("Employee not found"));

        if (!Boolean.TRUE.equals(employee.getActive())) {
            throw new ResourceNotFoundException("Employee not found");
        }

        // 2️⃣ Enforce one metric per employee per date
        metricRepository
                .findByEmployee_IdAndDate(employee.getId(), metric.getDate())
                .ifPresent(existing -> {
                    throw new IllegalStateException(
                            "Metric already exists for this employee and date"
                    );
                });

        // 3️⃣ Compute productivity score
        double score = ProductivityCalculator.computeScore(metric);
        metric.setProductivityScore(score);
        metric.setEmployee(employee);

        // 4️⃣ Save metric
        ProductivityMetricRecord savedMetric = metricRepository.save(metric);

        // 5️⃣ Apply active anomaly rules
        List<AnomalyRule> activeRules = anomalyRuleRepository.findByActiveTrue();

        for (AnomalyRule rule : activeRules) {

            boolean triggered = false;

            if ("SCORE_BELOW".equalsIgnoreCase(rule.getThresholdType())) {
                if (savedMetric.getProductivityScore() < rule.getThresholdValue()) {
                    triggered = true;
                }
            }

            if (triggered) {
                AnomalyFlagRecord flag = new AnomalyFlagRecord(
                        employee,
                        savedMetric,
                        rule.getRuleCode(),
                        "LOW",
                        rule.getDescription()
                );
                anomalyFlagRepository.save(flag);
            }
        }

        return savedMetric;
    }

    @Override
    public ProductivityMetricRecord updateMetric(
            Long id,
            ProductivityMetricRecord updated
    ) {
        ProductivityMetricRecord existing = metricRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Metric not found"));

        existing.setHoursLogged(updated.getHoursLogged());
        existing.setTasksCompleted(updated.getTasksCompleted());
        existing.setMeetingsAttended(updated.getMeetingsAttended());
        existing.setRawDataJson(updated.getRawDataJson());

        double score = ProductivityCalculator.computeScore(existing);
        existing.setProductivityScore(score);

        return metricRepository.save(existing);
    }

    @Override
    public List<ProductivityMetricRecord> getMetricsByEmployee(Long employeeId) {
        return metricRepository.findByEmployee_Id(employeeId);
    }

    @Override
    public Optional<ProductivityMetricRecord> getMetricById(Long id) {
        return metricRepository.findById(id);
    }

    @Override
    public List<ProductivityMetricRecord> getAllMetrics() {
        return metricRepository.findAll();
    }
}
