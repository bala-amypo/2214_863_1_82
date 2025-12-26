package com.example.demo.service;

import com.example.demo.model.ProductivityMetricRecord;

import java.util.List;

public interface ProductivityMetricService {

    ProductivityMetricRecord recordMetric(ProductivityMetricRecord metric);

    List<ProductivityMetricRecord> getAllMetrics();

    // 🔥 ADD THIS METHOD (REQUIRED)
    List<ProductivityMetricRecord> getMetricsByEmployee(Long employeeId);
}
