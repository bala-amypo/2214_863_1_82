package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.ProductivityMetricRecord;

public interface ProductivityMetricService {

    ProductivityMetricRecord recordMetric(ProductivityMetricRecord record);

    Optional<ProductivityMetricRecord> getMetricById(Long id);

    List<ProductivityMetricRecord> getAllMetrics();

    // 🔹 Added (upgrade)
    List<ProductivityMetricRecord> getMetricsByEmployee(Long employeeId);

    ProductivityMetricRecord updateMetric(Long id, ProductivityMetricRecord record);
}
