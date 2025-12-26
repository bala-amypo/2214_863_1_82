package com.example.demo.repository;

import com.example.demo.model.ProductivityMetricRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductivityMetricRecordRepository
        extends JpaRepository<ProductivityMetricRecord, Long> {

    // 🔹 ALREADY USED BY EXISTING SERVICE
    List<ProductivityMetricRecord> findByEmployeeId(Long employeeId);

    // 🔹 REQUIRED FOR TEAM SUMMARY (ADD THIS)
    List<ProductivityMetricRecord> findByEmployeeIdIn(List<Long> employeeIds);
}
