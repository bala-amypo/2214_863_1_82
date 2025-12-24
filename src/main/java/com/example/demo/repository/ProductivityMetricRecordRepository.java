package com.example.demo.repository;

import com.example.demo.model.ProductivityMetricRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ProductivityMetricRecordRepository
        extends JpaRepository<ProductivityMetricRecord, Long> {

    Optional<ProductivityMetricRecord> findByEmployee_IdAndDate(Long employeeId, LocalDate date);

    List<ProductivityMetricRecord> findByEmployee_Id(Long employeeId);
}
