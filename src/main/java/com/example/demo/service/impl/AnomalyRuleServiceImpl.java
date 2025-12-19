package com.example.demo.service.impl;

import com.example.demo.model.AnomalyRule;
import com.example.demo.repository.AnomalyRuleRepository;
import com.example.demo.service.AnomalyRuleService;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AnomalyRuleServiceImpl implements AnomalyRuleService {

    private final AnomalyRuleRepository anomalyRuleRepository;

    public AnomalyRuleServiceImpl(AnomalyRuleRepository anomalyRuleRepository) {
        this.anomalyRuleRepository = anomalyRuleRepository;
    }

    @Override
    public AnomalyRule createRule(AnomalyRule rule) {
        return anomalyRuleRepository.save(rule);
    }

    @Override
    public List<AnomalyRule> getActiveRules() {
        return anomalyRuleRepository.findByActiveTrue();
    }

    @Override
    public AnomalyRule getRuleById(Long id) {
        return anomalyRuleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rule not found"));
    }
}