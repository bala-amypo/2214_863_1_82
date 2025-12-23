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

        // ✅ Calculate productivity score
        double score = (record.getHoursLogged() * 10)
                     + (record.getTasksCompleted() * 5)
                     + (record.getMeetingsAttended() * 2);

        // ✅ Clamp score between 0 and 100
        score = Math.max(0, Math.min(score, 100));

        record.setProductivityScore(score);

        // ✅ Set submitted time automatically
        record.setSubmittedAt(LocalDateTime.now());

        return repository.save(record);
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
}
