package com.example.demo.service;

import java.util.List;
import com.example.demo.model.AnomalyFlagRecord;

public interface AnomalyFlagService {

    AnomalyFlagRecord createFlag(AnomalyFlagRecord flag);

    List<AnomalyFlagRecord> getAllFlags();

    AnomalyFlagRecord getFlagById(Long id);

    List<AnomalyFlagRecord> getFlagsByEmployeeId(Long employeeId);

    List<AnomalyFlagRecord> getFlagsByMetricId(Long metricId);

    AnomalyFlagRecord resolveFlag(Long id);
}
