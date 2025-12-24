package com.example.demo.service;

import java.util.List;

import com.example.demo.model.AnomalyRule;

public interface AnomalyRuleService {

    AnomalyRule createRule(AnomalyRule rule);

    List<AnomalyRule> getAllRules();

    List<AnomalyRule> getActiveRules();

    AnomalyRule getRuleById(Long id);

    AnomalyRule updateRule(Long id, AnomalyRule rule);

    void deleteRule(Long id);

    boolean evaluateRule(
            AnomalyRule rule,
            Integer productivityScore,
            Integer hoursLogged,
            Integer meetingsAttended
    );
}
