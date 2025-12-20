package com.example.demo.service.impl;

import com.example.demo.model.ProductivityMetricRecord;
import com.example.demo.repository.ProductivityMetricRecordRepository;
import com.example.demo.service.ProductivityMetricService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductivityMetricServiceImpl implements ProductivityMetricService {

    private final ProductivityMetricRecordRepository productivityMetricRecordRepository;

    public ProductivityMetricServiceImpl(
            ProductivityMetricRecordRepository productivityMetricRecordRepository) {
        this.productivityMetricRecordRepository = productivityMetricRecordRepository;
    }

    @Override
    public List<ProductivityMetricRecord> getMetricsForEmployee(Long employeeId) {
        return productivityMetricRecordRepository
                .findByEmployeeProfile_Id(employeeId);
    }

    @Override
    public ProductivityMetricRecord submitMetric(ProductivityMetricRecord record) {
        return productivityMetricRecordRepository.save(record);
    }
}
