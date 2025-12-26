package com.example.demo.service;

import java.util.List;

import com.example.demo.model.AnomalyFlagRecord;

public interface AnomalyFlagService {

    AnomalyFlagRecord flagAnomaly(AnomalyFlagRecord record);

    List<AnomalyFlagRecord> getAllFlags();

    AnomalyFlagRecord resolveFlag(Long id);
List<AnomalyFlagRecord> getByEmployee(Long employeeId);
List<AnomalyFlagRecord> getByMetric(Long metricId);

}
