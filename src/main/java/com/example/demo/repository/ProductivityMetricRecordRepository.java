package com.example.demo.repository;

import com.example.demo.model.ProductivityMetricRecord;
import java.util.List;

public interface ProductivityMetricRecordRepository {
    List<ProductivityMetricRecord> findByEmployeeId(Long employeeId);
}
