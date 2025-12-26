package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.AnomalyFlagRecord;
import com.example.demo.repository.AnomalyFlagRecordRepository;
import com.example.demo.service.AnomalyFlagService;

@Service
public class AnomalyFlagServiceImpl implements AnomalyFlagService {

    private final AnomalyFlagRecordRepository repository;

    public AnomalyFlagServiceImpl(AnomalyFlagRecordRepository repository) {
        this.repository = repository;
    }

    @Override
    public AnomalyFlagRecord flagAnomaly(AnomalyFlagRecord record) {
        return repository.save(record);
    }

    @Override
    public List<AnomalyFlagRecord> getAllFlags() {
        return repository.findAll();
    }
    @Override
public AnomalyFlagRecord resolveFlag(Long id) {
    AnomalyFlagRecord f = flagRepo.findById(id).orElseThrow();
    f.setResolved(true);
    return flagRepo.save(f);
}

@Override
public List<AnomalyFlagRecord> getByEmployee(Long employeeId) {
    return flagRepo.findByEmployeeId(employeeId);
}

@Override
public List<AnomalyFlagRecord> getByMetric(Long metricId) {
    return flagRepo.findByMetricId(metricId);
}

}
