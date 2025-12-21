package com.example.demo.service.impl;

import com.example.demo.model.ProductivityMetricRecord;
import com.example.demo.repository.ProductivityMetricRepository;
import com.example.demo.service.ProductivityMetricService;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductivityMetricServiceImpl implements ProductivityMetricService {

    private final ProductivityMetricRepository repository;

    public ProductivityMetricServiceImpl(ProductivityMetricRepository repository) {
        this.repository = repository;
    }

    @Override
    public ProductivityMetricRecord recordMetric(ProductivityMetricRecord record) {
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
