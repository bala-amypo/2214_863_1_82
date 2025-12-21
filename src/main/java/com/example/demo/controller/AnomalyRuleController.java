package com.example.demo.controller;

import com.example.demo.model.AnomalyRule;
import com.example.demo.service.AnomalyRuleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/anomaly-rules")
public class AnomalyRuleController {

    private final AnomalyRuleService service;

    public AnomalyRuleController(AnomalyRuleService service) {
        this.service = service;
    }

    @PostMapping
    public AnomalyRule createRule(@RequestBody AnomalyRule rule) {
        return service.createRule(rule);
    }

    @GetMapping("/active")
    public List<AnomalyRule> getActiveRules() {
        return service.getActiveRules();
    }
}
