package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.AnomalyRule;
import com.example.demo.repository.AnomalyRuleRepository;
import com.example.demo.service.AnomalyRuleService;

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
    public List<AnomalyRule> getActiveRules() {
        return repository.findByActiveTrue();
    }

    @Override
public AnomalyRule updateRule(Long id, AnomalyRule rule) {
    rule.setId(id);
    return ruleRepo.save(rule);
}

}
