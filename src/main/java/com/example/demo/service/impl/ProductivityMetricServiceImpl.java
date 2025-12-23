package com.example.demo.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.EmployeeProfile;
import com.example.demo.model.ProductivityMetricRecord;
import com.example.demo.repository.EmployeeProfileRepository;
import com.example.demo.repository.ProductivityMetricRecordRepository;
import com.example.demo.service.ProductivityMetricService;
import com.example.demo.util.ProductivityCalculator;

@Service
@Transactional
public class ProductivityMetricServiceImpl implements ProductivityMetricService {

    private final ProductivityMetricRecordRepository metricRepository;
    private final EmployeeProfileRepository employeeRepository;

    public ProductivityMetricServiceImpl(
            ProductivityMetricRecordRepository metricRepository,
            EmployeeProfileRepository employeeRepository) {
        this.metricRepository = metricRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public ProductivityMetricRecord createMetric(ProductivityMetricRecord metric) {

        EmployeeProfile employee = employeeRepository
                .findById(metric.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        if (!employee.getActive()) {
            throw new IllegalStateException("Employee not active");
        }

        boolean exists = metricRepository
                .findByEmployeeIdAndDate(metric.getEmployeeId(), metric.getDate())
                .isPresent();

        if (exists) {
            throw new IllegalStateException("Metric already exists for this employee and date");
        }

        double score = ProductivityCalculator.computeScore(metric);
        metric.setProductivityScore(score);
        metric.setSubmittedAt(LocalDateTime.now());

        return metricRepository.save(metric);
    }

    @Override
    public ProductivityMetricRecord updateMetric(Long id, ProductivityMetricRecord updated) {

        ProductivityMetricRecord existing = metricRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Metric not found"));

        existing.setDate(updated.getDate());
        existing.setHoursLogged(updated.getHoursLogged());
        existing.setTasksCompleted(updated.getTasksCompleted());
        existing.setMeetingsAttended(updated.getMeetingsAttended());
        existing.setRawDataJson(updated.getRawDataJson());

        double score = ProductivityCalculator.computeScore(existing);
        existing.setProductivityScore(score);

        return metricRepository.save(existing);
    }

    @Override
    public ProductivityMetricRecord getMetricById(Long id) {
        return metricRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Metric not found"));
    }

    @Override
    public List<ProductivityMetricRecord> getMetricsByEmployee(Long employeeId) {
        return metricRepository.findByEmployeeId(employeeId);
    }

    @Override
    public List<ProductivityMetricRecord> getAllMetrics() {
        return metricRepository.findAll();
    }
}
