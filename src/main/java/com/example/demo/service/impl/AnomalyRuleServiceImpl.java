package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.AnomalyRule;
import com.example.demo.repository.AnomalyRuleRepository;
import com.example.demo.service.AnomalyRuleService;

@Service
public class AnomalyRuleServiceImpl implements AnomalyRuleService {

    private final AnomalyRuleRepository repository;

    public AnomalyRuleServiceImpl(AnomalyRuleRepository repository) {
        this.repository = repository;
    }

    // ---------------- CRUD ----------------

    @Override
    public AnomalyRule createRule(AnomalyRule rule) {
        rule.setActive(true); // tests expect active by default
        return repository.save(rule);
    }

    @Override
    public List<AnomalyRule> getAllRules() {
        return repository.findAll();
    }

    @Override
    public AnomalyRule getRuleById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Rule not found with id " + id));
    }

    @Override
    public AnomalyRule updateRule(Long id, AnomalyRule updated) {
        AnomalyRule existing = getRuleById(id);

        existing.setRuleCode(updated.getRuleCode());
        existing.setDescription(updated.getDescription());
        existing.setThresholdType(updated.getThresholdType());
        existing.setThresholdValue(updated.getThresholdValue());
        existing.setActive(updated.getActive());

        return repository.save(existing);
    }

    @Override
    public void deleteRule(Long id) {
        AnomalyRule rule = getRuleById(id);
        repository.delete(rule);
    }

    // ---------------- RULE EVALUATION ----------------

    @Override
    public boolean evaluateRule(
            AnomalyRule rule,
            Integer productivityScore,
            Integer hoursLogged,
            Integer meetingsAttended
    ) {

        if (!Boolean.TRUE.equals(rule.getActive())) {
            return false;
        }

        Integer threshold = rule.getThresholdValue();

        return switch (rule.getThresholdType()) {

            case "SCORE_BELOW" ->
                    productivityScore != null && productivityScore < threshold;

            case "SCORE_ABOVE" ->
                    productivityScore != null && productivityScore > threshold;

            case "HOURS_BELOW" ->
                    hoursLogged != null && hoursLogged < threshold;

            case "HOURS_ABOVE" ->
                    hoursLogged != null && hoursLogged > threshold;

            case "MEETINGS_ABOVE" ->
                    meetingsAttended != null && meetingsAttended > threshold;

            default -> false;
        };
    }
}
