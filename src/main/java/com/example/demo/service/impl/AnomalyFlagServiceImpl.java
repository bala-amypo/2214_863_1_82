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
}
