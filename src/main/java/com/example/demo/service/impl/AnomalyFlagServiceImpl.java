package com.example.demo.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.AnomalyFlagRecord;
import com.example.demo.repository.AnomalyFlagRecordRepository;
import com.example.demo.service.AnomalyFlagService;

@Service
public class AnomalyFlagServiceImpl implements AnomalyFlagService {

    private final AnomalyFlagRecordRepository repository;

    public AnomalyFlagServiceImpl(AnomalyFlagRecordRepository repository) {
        this.repository = repository;
    }

    // ---------------- CREATE ----------------
    @Override
    public AnomalyFlagRecord createFlag(AnomalyFlagRecord flag) {
        flag.setResolved(false);
        flag.setFlaggedAt(LocalDateTime.now());
        return repository.save(flag);
    }

    // ---------------- READ ----------------
    @Override
    public List<AnomalyFlagRecord> getAllFlags() {
        return repository.findAll();
    }

    @Override
    public AnomalyFlagRecord getFlagById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Anomaly not found with id " + id));
    }

    @Override
    public List<AnomalyFlagRecord> getFlagsByEmployeeId(Long employeeId) {
        return repository.findByEmployeeId(employeeId);
    }

    @Override
    public List<AnomalyFlagRecord> getFlagsByMetricId(Long metricId) {
        return repository.findByMetricId(metricId);
    }

    // ---------------- RESOLVE ----------------
    @Override
    public AnomalyFlagRecord resolveFlag(Long id) {
        AnomalyFlagRecord flag = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Anomaly not found with id " + id));

        flag.setResolved(true);
        return repository.save(flag);
    }
}
