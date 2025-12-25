package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.AnomalyFlagRecord;

@Repository
public interface AnomalyFlagRecordRepository
        extends JpaRepository<AnomalyFlagRecord, Long> {

    List<AnomalyFlagRecord> findByMetricId(Long metricId);
}
