package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.ProductivityMetricRecord;
import com.example.demo.repository.ProductivityMetricRecordRepository;
import com.example.demo.service.ProductivityMetricService;
import com.example.demo.util.ProductivityCalculator;

@Service
@Transactional
public class ProductivityMetricServiceImpl implements ProductivityMetricService {

    private final ProductivityMetricRecordRepository repository;

    public ProductivityMetricServiceImpl(
            ProductivityMetricRecordRepository repository) {
        this.repository = repository;
    }

    // ===================== POST =====================
    @Override
    public ProductivityMetricRecord createMetric(ProductivityMetricRecord metric) {

        repository.findByEmployeeIdAndDate(
                metric.getEmployeeId(),
                metric.getDate()
        ).ifPresent(m -> {
            throw new IllegalStateException(
                    "Metric already exists for this employee and date");
        });

        Double score = ProductivityCalculator.computeScore(metric);
        metric.setProductivityScore(score);

        return repository.save(metric);
    }

    // ===================== PUT =====================
    @Override
    public ProductivityMetricRecord updateMetric(
            Long id,
            ProductivityMetricRecord updated) {

        ProductivityMetricRecord existing =
                repository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException("Metric not found"));

        existing.setDate(updated.getDate());
        existing.setHoursLogged(updated.getHoursLogged());
        existing.setTasksCompleted(updated.getTasksCompleted());
        existing.setMeetingsAttended(updated.getMeetingsAttended());
        existing.setRawDataJson(updated.getRawDataJson());

        Double score = ProductivityCalculator.computeScore(existing);
        existing.setProductivityScore(score);

        return repository.save(existing);
    }

    // ===================== GET ALL =====================
    @Override
    public List<ProductivityMetricRecord> getAllMetrics() {
        return repository.findAll();
    }
}
