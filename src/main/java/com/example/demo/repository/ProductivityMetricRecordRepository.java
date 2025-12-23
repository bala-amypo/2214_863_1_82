package com.example.demo.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.ProductivityMetricRecord;

public interface ProductivityMetricRecordRepository
        extends JpaRepository<ProductivityMetricRecord, Long> {

    Optional<ProductivityMetricRecord> findByEmployeeIdAndDate(
            Long employeeId, LocalDate date);
}
