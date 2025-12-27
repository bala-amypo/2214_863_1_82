package com.example.demo.service;

import com.example.demo.model.ProductivityMetricRecord;

import java.util.List;
import java.util.Optional;

public interface ProductivityMetricService {

    ProductivityMetricRecord recordMetric(ProductivityMetricRecord metric);

    List<ProductivityMetricRecord> getAllMetrics();

    List<ProductivityMetricRecord> getMetricsByEmployee(Long employeeId);

    Optional<ProductivityMetricRecord> getMetricById(Long id);
}
