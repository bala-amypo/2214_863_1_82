package com.example.demo.service;

import com.example.demo.model.ProductivityMetricRecord;

import java.util.List;

public interface ProductivityService {
    ProductivityMetricRecord submit(ProductivityMetricRecord record);
    List<ProductivityMetricRecord> getByEmployee(Long employeeId);
}
