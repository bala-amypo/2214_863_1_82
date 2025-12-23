package com.example.demo.service.impl;

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

    // ✅ POST LOGIC
    @Override
    public ProductivityMetricRecord createMetric(ProductivityMetricRecord metric) {

        // prevent duplicate metric for same day
        repository.findByEmployeeIdAndDate(
                metric.getEmployeeId(), metric.getDate()
        ).ifPresent(m -> {
            throw new IllegalStateException(
                    "Metric already exists for this employee and date");
        });

        // calculate score (ignore input score)
        Double score = ProductivityCalculator.computeScore(metric);
        metric.setProductivityScore(score);

        return repository.save(metric);
    }

    // ✅ PUT LOGIC
    @Override
    public ProductivityMetricRecord updateMetric(
            Long id, ProductivityMetricRecord updated) {

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
}
