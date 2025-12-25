package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.ProductivityMetricRecord;

@Repository
public interface ProductivityMetricRecordRepository
        extends JpaRepository<ProductivityMetricRecord, Long> {

    List<ProductivityMetricRecord> findByEmployeeId(Long employeeId);
}
