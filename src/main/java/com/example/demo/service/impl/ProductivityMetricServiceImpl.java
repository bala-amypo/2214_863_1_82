package com.example.demo.service.impl;

import com.example.demo.model.ProductivityMetricRecord;
import com.example.demo.repository.ProductivityMetricRecordRepository;
import com.example.demo.service.ProductivityMetricService;
import com.example.demo.util.ProductivityCalculator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductivityMetricServiceImpl implements ProductivityMetricService {

    private final ProductivityMetricRecordRepository metricRepository;

    public ProductivityMetricServiceImpl(ProductivityMetricRecordRepository metricRepository) {
        this.metricRepository = metricRepository;
    }

    // ✅ THIS METHOD EXISTS IN INTERFACE
    @Override
    public ProductivityMetricRecord recordMetric(ProductivityMetricRecord metric) {

        double score = ProductivityCalculator.computeScore(
                metric.getHoursLogged(),
                metric.getTasksCompleted(),
                metric.getMeetingsAttended()
        );

        metric.setProductivityScore(score);
        return metricRepository.save(metric);
    }

    // ✅ THIS METHOD EXISTS IN INTERFACE
    @Override
    public List<ProductivityMetricRecord> getAllMetrics() {
        return metricRepository.findAll();
    }

    // ❗ NOT DECLARED IN INTERFACE → NO @Override
    public Optional<ProductivityMetricRecord> getMetricById(Long id) {
        return metricRepository.findById(id);
    }

    // ❗ NOT DECLARED IN INTERFACE → NO @Override
    public List<ProductivityMetricRecord> getMetricsByEmployee(Long employeeId) {
        return metricRepository.findByEmployeeId(employeeId);
    }

    // ❗ NOT DECLARED IN INTERFACE → NO @Override
    public ProductivityMetricRecord updateMetric(Long id, ProductivityMetricRecord updated) {

        ProductivityMetricRecord existing = metricRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Metric not found"));

        existing.setHoursLogged(updated.getHoursLogged());
        existing.setTasksCompleted(updated.getTasksCompleted());
        existing.setMeetingsAttended(updated.getMeetingsAttended());
        existing.setRawDataJson(updated.getRawDataJson());

        double score = ProductivityCalculator.computeScore(
                existing.getHoursLogged(),
                existing.getTasksCompleted(),
                existing.getMeetingsAttended()
        );

        existing.setProductivityScore(score);
        return metricRepository.save(existing);
    }
}
