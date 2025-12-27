package com.example.demo.controller;

import com.example.demo.model.AnomalyRule;
import com.example.demo.repository.AnomalyRuleRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/anomaly-rules")
public class AnomalyRuleController {

    private final AnomalyRuleRepository anomalyRuleRepository;

    public AnomalyRuleController(AnomalyRuleRepository anomalyRuleRepository) {
        this.anomalyRuleRepository = anomalyRuleRepository;
    }

    @PostMapping
    public AnomalyRule create(@RequestBody AnomalyRule request) {

        AnomalyRule rule = new AnomalyRule();
        rule.setRuleCode(request.getRuleCode());
        rule.setThresholdValue(request.getThresholdValue());
        rule.setActive(request.getActive());

        return anomalyRuleRepository.save(rule);
    }

    @PutMapping("/{id}")
    public AnomalyRule update(@PathVariable Long id,
                              @RequestBody AnomalyRule request) {

        AnomalyRule rule = anomalyRuleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rule not found"));

        rule.setRuleCode(request.getRuleCode());
        rule.setThresholdValue(request.getThresholdValue());
        rule.setActive(request.getActive());

        return anomalyRuleRepository.save(rule);
    }

    @GetMapping("/{id}")
    public AnomalyRule getById(@PathVariable Long id) {
        return anomalyRuleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rule not found"));
    }

    @GetMapping
    public List<AnomalyRule> getAll() {
        return anomalyRuleRepository.findAll();
    }

    @GetMapping("/active")
    public List<AnomalyRule> getActiveRules() {
        return anomalyRuleRepository.findByActiveTrue();
    }
}
