package com.example.demo.service.impl;

import com.example.demo.model.AnomalyRule;
import com.example.demo.repository.AnomalyRuleRepository;
import com.example.demo.service.AnomalyRuleService;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnomalyRuleServiceImpl implements AnomalyRuleService {

    private final AnomalyRuleRepository repository;

    public AnomalyRuleServiceImpl(AnomalyRuleRepository repository) {
        this.repository = repository;
    }

    @Override
    public AnomalyRule createRule(AnomalyRule rule) {
        return repository.save(rule);
    }

    @Override
    public AnomalyRule updateRule(Long id, AnomalyRule updated) {
        AnomalyRule rule = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rule not found"));

        rule.setRuleCode(updated.getRuleCode());
        rule.setDescription(updated.getDescription());
        rule.setThresholdType(updated.getThresholdType());
        rule.setThresholdValue(updated.getThresholdValue());
        rule.setActive(updated.getActive());

        return repository.save(rule);
    }

    @Override
    public List<AnomalyRule> getActiveRules() {
        return repository.findByActiveTrue();
    }

    @Override
    public List<AnomalyRule> getAllRules() {
        return repository.findAll();
    }
}
