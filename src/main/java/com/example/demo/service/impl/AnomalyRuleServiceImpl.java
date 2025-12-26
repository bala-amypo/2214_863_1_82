package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.AnomalyRule;
import com.example.demo.repository.AnomalyRuleRepository;
import com.example.demo.service.AnomalyRuleService;

@Service
public class AnomalyRuleServiceImpl implements AnomalyRuleService {

    private final AnomalyRuleRepository ruleRepo;

    public AnomalyRuleServiceImpl(AnomalyRuleRepository ruleRepo) {
        this.ruleRepo = ruleRepo;
    }

    @Override
    public AnomalyRule createRule(AnomalyRule rule) {
        return ruleRepo.save(rule);
    }

    @Override
    public List<AnomalyRule> getActiveRules() {
        return ruleRepo.findByActiveTrue();
    }

    // 🔹 Added (upgrade)
    @Override
    public AnomalyRule updateRule(Long id, AnomalyRule rule) {
        rule.setId(id);
        return ruleRepo.save(rule);
    }
}