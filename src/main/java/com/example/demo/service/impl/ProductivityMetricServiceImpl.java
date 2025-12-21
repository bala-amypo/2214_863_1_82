package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.ProductivityMetricRecord;
import com.example.demo.repository.ProductivityMetricRecordRepository;
import com.example.demo.service.ProductivityService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductivityServiceImpl implements ProductivityService {

    private final ProductivityMetricRecordRepository repository;

    public ProductivityServiceImpl(ProductivityMetricRecordRepository repository) {
        this.repository = repository;
    }

    public ProductivityMetricRecord submit(ProductivityMetricRecord record) {
        return repository.save(record);
    }

    public List<ProductivityMetricRecord> getByEmployee(Long employeeId) {
        return repository.findByEmployeeId(employeeId);
    }
}
