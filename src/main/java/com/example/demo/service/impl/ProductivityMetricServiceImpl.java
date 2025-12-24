package com.example.demo.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.model.AnomalyFlagRecord;
import com.example.demo.model.ProductivityMetricRecord;
import com.example.demo.repository.AnomalyFlagRecordRepository;
import com.example.demo.repository.ProductivityMetricRecordRepository;
import com.example.demo.service.ProductivityMetricService;

@Service
public class ProductivityMetricServiceImpl implements ProductivityMetricService {

    private final ProductivityMetricRecordRepository metricRepository;
    private final AnomalyFlagRecordRepository anomalyFlagRepository;

    public ProductivityMetricServiceImpl(
            ProductivityMetricRecordRepository metricRepository,
            AnomalyFlagRecordRepository anomalyFlagRepository) {
        this.metricRepository = metricRepository;
        this.anomalyFlagRepository = anomalyFlagRepository;
    }

    @Override
    public ProductivityMetricRecord createMetric(ProductivityMetricRecord record) {

        ProductivityMetricRecord saved = metricRepository.save(record);

        // ---- anomaly rule: LOW SCORE ----
        if (saved.getProductivityScore() < 40) {
            AnomalyFlagRecord flag = new AnomalyFlagRecord();
            flag.setMetricId(saved.getId());
            flag.setRuleCode("LOW_SCORE");
            flag.setDescription("Productivity score below threshold");
            flag.setResolved(false);
            flag.setFlaggedAt(LocalDateTime.now());

            anomalyFlagRepository.save(flag);
        }

        return saved;
    }

    @Override
    public List<ProductivityMetricRecord> getAllMetrics() {
        return metricRepository.findAll();
    }

    @Override
    public Optional<ProductivityMetricRecord> getMetricById(Long id) {
        return metricRepository.findById(id);
    }

    @Override
    public List<ProductivityMetricRecord> getMetricsByEmployee(Long employeeId) {
        return metricRepository.findByEmployeeId(employeeId);
    }
}
