package com.example.demo.service.impl;

import com.example.demo.model.AnomalyFlagRecord;
import com.example.demo.repository.AnomalyFlagRecordRepository;
import com.example.demo.repository.EmployeeProfileRepository;
import com.example.demo.repository.AnomalyRuleRepository;
import com.example.demo.service.AnomalyFlagService;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AnomalyFlagServiceImpl implements AnomalyFlagService {

    private final AnomalyFlagRecordRepository anomalyFlagRecordRepository;
    private final EmployeeProfileRepository employeeProfileRepository;
    private final AnomalyRuleRepository anomalyRuleRepository;

    public AnomalyFlagServiceImpl(AnomalyFlagRecordRepository anomalyFlagRecordRepository,
                                EmployeeProfileRepository employeeProfileRepository,
                                AnomalyRuleRepository anomalyRuleRepository) {
        this.anomalyFlagRecordRepository = anomalyFlagRecordRepository;
        this.employeeProfileRepository = employeeProfileRepository;
        this.anomalyRuleRepository = anomalyRuleRepository;
    }

    @Override
    public AnomalyFlagRecord flagAnomaly(AnomalyFlagRecord record) {
        if (!employeeProfileRepository.existsById(record.getEmployeeProfile().getId())) {
            throw new ResourceNotFoundException("Employee not found");
        }
        if (!anomalyRuleRepository.existsById(record.getAnomalyRule().getId())) {
            throw new ResourceNotFoundException("Rule not found");
        }
        return anomalyFlagRecordRepository.save(record);
    }

    @Override
    public List<AnomalyFlagRecord> getUnresolvedFlags() {
        return anomalyFlagRecordRepository.findByResolvedFalse();
    }
}