package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.model.AnomalyRule;
import com.example.demo.service.AnomalyRuleService;

@RestController
@RequestMapping("/api/anomaly-rules")
public class AnomalyRuleController {

    private final AnomalyRuleService service;

    public AnomalyRuleController(AnomalyRuleService service) {
        this.service = service;
    }

    // ---------------- BASIC ENDPOINTS ----------------

    @PostMapping
    public AnomalyRule create(@RequestBody AnomalyRule rule) {
        return service.createRule(rule);
    }

    @GetMapping
    public List<AnomalyRule> getActiveRules() {
        return service.getActiveRules();
    }

    // ----------- ADDITIONAL ENDPOINTS (Swagger completeness) -----------

    @GetMapping("/active")
    public List<AnomalyRule> listOnlyActiveRules() {
        return service.getActiveRules();
    }

    @PutMapping("/{id}")
    public AnomalyRule updateRule(
            @PathVariable Long id,
            @RequestBody AnomalyRule rule) {
        return service.updateRule(id, rule);
    }
}
