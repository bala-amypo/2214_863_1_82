package com.example.demo.repository;

import com.example.demo.model.ProductivityMetricRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductivityMetricRecordRepository
        extends JpaRepository<ProductivityMetricRecord, Long> {

    // ✅ REQUIRED by existing service code (DO NOT REMOVE)
    List<ProductivityMetricRecord> findByEmployeeId(Long employeeId);

    // ✅ Used for team summary aggregation
    List<ProductivityMetricRecord> findByEmployeeIdIn(List<Long> employeeIds);
}
