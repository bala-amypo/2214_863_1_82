package com.example.demo.service;

import java.util.List;

import com.example.demo.model.AnomalyFlagRecord;

public interface AnomalyFlagService {

    AnomalyFlagRecord flagAnomaly(AnomalyFlagRecord record);

    List<AnomalyFlagRecord> getAllFlags();

    List<AnomalyFlagRecord> getFlagsByEmployee(Long employeeId);

    List<AnomalyFlagRecord> getFlagsByMetric(Long metricId);

    AnomalyFlagRecord resolveFlag(Long id);
}
