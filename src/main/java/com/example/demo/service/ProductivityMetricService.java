package com.example.demo.service;

import com.example.demo.model.ProductivityMetricRecord;

import java.util.List;
import java.util.Optional;

public interface ProductivityMetricService {

    ProductivityMetricRecord recordMetric(ProductivityMetricRecord metric);

    List<ProductivityMetricRecord> getAllMetrics();

    // 🔥 REQUIRED for GET /api/metrics/employee/{id}
    List<ProductivityMetricRecord> getMetricsByEmployee(Long employeeId);

    // 🔥 REQUIRED for GET /api/metrics/{id}
    Optional<ProductivityMetricRecord> getMetricById(Long id);
}
