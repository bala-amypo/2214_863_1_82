package com.example.demo.service.impl;

import com.example.demo.model.AnomalyFlagRecord;
import com.example.demo.repository.AnomalyFlagRecordRepository;
import com.example.demo.service.AnomalyFlagService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnomalyFlagServiceImpl implements AnomalyFlagService {

    private final AnomalyFlagRecordRepository repository;

    public AnomalyFlagServiceImpl(AnomalyFlagRecordRepository repository) {
        this.repository = repository;
    }

    public AnomalyFlagRecord create(AnomalyFlagRecord flag) {
        return repository.save(flag);
    }

    public List<AnomalyFlagRecord> getUnresolved() {
        return repository.findByResolvedFalse();
    }
}
