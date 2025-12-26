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

    // =====================================================
    // CREATE / RECORD METRIC
    // =====================================================
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

    // =====================================================
    // GET ALL METRICS
    // =====================================================
    @Override
    public List<ProductivityMetricRecord> getAllMetrics() {
        return metricRepository.findAll();
    }

    // =====================================================
    // GET METRIC BY ID
    // (Used by controller, not declared in interface)
    // =====================================================
    public Optional<ProductivityMetricRecord> getMetricById(Long id) {
        return metricRepository.findById(id);
    }

    // =====================================================
    // GET METRICS BY EMPLOYEE ID  ✅ FIXED
    // =====================================================
    public List<ProductivityMetricRecord> getMetricsByEmployee(Long employeeId) {

        // 🔹 Primary query (normal case)
        List<ProductivityMetricRecord> metrics =
                metricRepository.findByEmployeeId(employeeId);

        // 🔹 SAFETY FALLBACK (prevents empty list issue in Swagger)
        if (metrics == null || metrics.isEmpty()) {
            return metricRepository.findAll().stream()
                    .filter(m -> m.getEmployeeId() != null
                            && m.getEmployeeId().equals(employeeId))
                    .toList();
        }

        return metrics;
    }

    // =====================================================
    // UPDATE METRIC
    // =====================================================
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
