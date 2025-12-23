package com.example.demo.service;

import java.util.List;

import com.example.demo.model.ProductivityMetricRecord;

public interface ProductivityMetricService {

    ProductivityMetricRecord createMetric(ProductivityMetricRecord record);

    ProductivityMetricRecord updateMetric(Long id, ProductivityMetricRecord record);

    ProductivityMetricRecord getMetricById(Long id);

    List<ProductivityMetricRecord> getMetricsByEmployee(Long employeeId);

    List<ProductivityMetricRecord> getAllMetrics();
}
