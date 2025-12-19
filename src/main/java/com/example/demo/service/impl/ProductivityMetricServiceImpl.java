package com.example.demo.service.impl;

import com.example.demo.model.ProductivityMetricRecord;
import com.example.demo.repository.ProductivityMetricRecordRepository;
import com.example.demo.repository.EmployeeProfileRepository;
import com.example.demo.service.ProductivityMetricService;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductivityMetricServiceImpl implements ProductivityMetricService {

    private final ProductivityMetricRecordRepository productivityMetricRecordRepository;
    private final EmployeeProfileRepository employeeProfileRepository;

    public ProductivityMetricServiceImpl(ProductivityMetricRecordRepository productivityMetricRecordRepository,
                                       EmployeeProfileRepository employeeProfileRepository) {
        this.productivityMetricRecordRepository = productivityMetricRecordRepository;
        this.employeeProfileRepository = employeeProfileRepository;
    }

    @Override
    public ProductivityMetricRecord submitMetric(ProductivityMetricRecord record) {
        if (!employeeProfileRepository.existsById(record.getEmployeeProfile().getId())) {
            throw new ResourceNotFoundException("Employee not found");
        }
        return productivityMetricRecordRepository.save(record);
    }

    @Override
    public List<ProductivityMetricRecord> getMetricsForEmployee(Long employeeId) {
        return productivityMetricRecordRepository.findByEmployeeId(employeeId);
    }
}