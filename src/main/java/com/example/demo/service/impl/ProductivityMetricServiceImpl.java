package com.example.demo.service.impl;

import com.example.demo.model.ProductivityMetricRecord;
import com.example.demo.repository.ProductivityMetricRecordRepository;
import com.example.demo.service.ProductivityMetricService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProductivityMetricServiceImpl implements ProductivityMetricService {

    private final ProductivityMetricRecordRepository repository;

    public ProductivityMetricServiceImpl(ProductivityMetricRecordRepository repository) {
        this.repository = repository;
    }

   
    @Override
    public ProductivityMetricRecord recordMetric(ProductivityMetricRecord record) {

        calculateScore(record);
        record.setSubmittedAt(LocalDateTime.now());

        return repository.save(record);
    }

    
    @Override
    public ProductivityMetricRecord updateMetric(Long id, ProductivityMetricRecord updated) {

        ProductivityMetricRecord existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Metric not found"));

        existing.setDate(updated.getDate());
        existing.setHoursLogged(updated.getHoursLogged());
        existing.setTasksCompleted(updated.getTasksCompleted());
        existing.setMeetingsAttended(updated.getMeetingsAttended());
        existing.setRawDataJson(updated.getRawDataJson());

        calculateScore(existing);

        return repository.save(existing);
    }

   
    @Override
    public List<ProductivityMetricRecord> getMetricsByEmployee(Long employeeId) {
        return repository.findByEmployeeId(employeeId);
    }

   
    @Override
    public List<ProductivityMetricRecord> getAllMetrics() {
        return repository.findAll();
    }

    
    @Override
    public Optional<ProductivityMetricRecord> getMetricById(Long id) {
        return repository.findById(id);
    }

    
    private void calculateScore(ProductivityMetricRecord record) {
        double score =
                (record.getHoursLogged() * 10) +
                (record.getTasksCompleted() * 5) +
                (record.getMeetingsAttended() * 2);

        score = Math.max(0, Math.min(score, 100));
        record.setProductivityScore(score);
    }
}
