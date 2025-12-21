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
        return anomalyRuleService.create(rule);
    }

    @GetMapping("/active")
    public List<AnomalyRule> getActiveRules() {
        return anomalyRuleService.getActiveRules();
    }
}
