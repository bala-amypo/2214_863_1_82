package com.example.demo.service;

import java.util.List;

import com.example.demo.model.AnomalyRule;

public interface AnomalyRuleService {

    AnomalyRule createRule(AnomalyRule rule);

    List<AnomalyRule> getActiveRules();

    AnomalyRule updateRule(Long id, AnomalyRule rule);
}
