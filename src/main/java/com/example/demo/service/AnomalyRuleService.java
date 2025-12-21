package com.example.demo.service;

import com.example.demo.model.AnomalyRule;

import java.util.List;

public interface AnomalyRuleService {
    AnomalyRule create(AnomalyRule rule);
    List<AnomalyRule> getActiveRules();
}
