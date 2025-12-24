package com.example.demo.repository;

import com.example.demo.model.AnomalyFlagRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnomalyFlagRecordRepository
        extends JpaRepository<AnomalyFlagRecord, Long> {

    List<AnomalyFlagRecord> findByEmployee_Id(Long employeeId);

    List<AnomalyFlagRecord> findByMetric_Id(Long metricId);

    List<AnomalyFlagRecord> findByResolvedFalse();
}