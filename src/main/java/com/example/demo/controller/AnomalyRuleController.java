package com.example.demo.controller;

import com.example.demo.model.AnomalyRule;
import com.example.demo.service.AnomalyRuleService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/anomaly-rules")
public class AnomalyRuleController {
    
    private final AnomalyRuleService anomalyRuleService;
    
    public AnomalyRuleController(AnomalyRuleService anomalyRuleService) {
        this.anomalyRuleService = anomalyRuleService;
    }
    
    @PostMapping
    public AnomalyRule createRule(@RequestBody AnomalyRule rule) {
        return anomalyRuleService.createRule(rule);
    }
    
    @PutMapping("/{id}")
    public AnomalyRule updateRule(@PathVariable Long id, @RequestBody AnomalyRule updatedRule) {
        return anomalyRuleService.updateRule(id, updatedRule);
    }
    
    @GetMapping("/active")
    public List<AnomalyRule> getActiveRules() {
        return anomalyRuleService.getActiveRules();
    }
    
    @GetMapping("/{id}")
    public AnomalyRule getRuleById(@PathVariable Long id) {
        return anomalyRuleService.getAllRules()
                .stream()
                .filter(rule -> rule.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Rule not found"));
    }
    
    @GetMapping
    public List<AnomalyRule> getAllRules() {
        return anomalyRuleService.getAllRules();
    }
}