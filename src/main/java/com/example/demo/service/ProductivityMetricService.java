package com.example.demo.service;

import java.util.List;

import com.example.demo.model.ProductivityMetricRecord;

public interface ProductivityMetricService {

    // POST
    ProductivityMetricRecord createMetric(ProductivityMetricRecord metric);

    // PUT
    ProductivityMetricRecord updateMetric(Long id, ProductivityMetricRecord metric);

    // GET ALL  ✅ THIS FIXES YOUR 500
    List<ProductivityMetricRecord> getAllMetrics();
}
