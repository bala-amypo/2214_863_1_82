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

    @Override
    public AnomalyRule createRule(AnomalyRule rule) {
        return repository.save(rule);
    }

    @Override
    public List<AnomalyRule> getAllRules() {
        return repository.findAll();
    }

    @Override
    public List<AnomalyRule> getActiveRules() {
        return repository.findByActiveTrue();
    }

    @Override
    public AnomalyRule getRuleById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rule not found"));
    }

    @Override
    public AnomalyRule updateRule(Long id, AnomalyRule rule) {
        AnomalyRule existing = getRuleById(id);

        existing.setRuleCode(rule.getRuleCode());
        existing.setDescription(rule.getDescription());
        existing.setThresholdType(rule.getThresholdType());
        existing.setThresholdValue(rule.getThresholdValue());
        existing.setActive(rule.getActive());

        return repository.save(existing);
    }

    @Override
    public void deleteRule(Long id) {
        AnomalyRule rule = getRuleById(id);
        repository.delete(rule);
    }

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

        Double threshold = rule.getThresholdValue();
        if (threshold == null) {
            return false;
        }

        String type = rule.getThresholdType();

        if ("SCORE_BELOW".equals(type)) {
            return productivityScore != null
                    && productivityScore.doubleValue() < threshold;
        }

        if ("SCORE_ABOVE".equals(type)) {
            return productivityScore != null
                    && productivityScore.doubleValue() > threshold;
        }

        if ("HOURS_BELOW".equals(type)) {
            return hoursLogged != null
                    && hoursLogged.doubleValue() < threshold;
        }

        if ("HOURS_ABOVE".equals(type)) {
            return hoursLogged != null
                    && hoursLogged.doubleValue() > threshold;
        }

        if ("MEETINGS_ABOVE".equals(type)) {
            return meetingsAttended != null
                    && meetingsAttended.doubleValue() > threshold;
        }

        return false;
    }
}
