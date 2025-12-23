package com.example.demo.service;

import com.example.demo.model.ProductivityMetricRecord;

public interface ProductivityMetricService {

    ProductivityMetricRecord updateMetric(Long id, ProductivityMetricRecord updatedMetric);
}
