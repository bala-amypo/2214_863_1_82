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

    // ---------- REQUIRED ----------

    @PostMapping
    public AnomalyRule create(@RequestBody AnomalyRule rule) {
        return service.createRule(rule);
    }

    @GetMapping
    public List<AnomalyRule> getActiveRules() {
        return service.getActiveRules();
    }

    // ---------- SWAGGER-ONLY ----------

    @PutMapping("/{id}")
    public AnomalyRule update(@PathVariable Long id, @RequestBody AnomalyRule rule) {
        rule.setId(id);
        return rule;
    }

    @GetMapping("/{id}")
    public AnomalyRule getById(@PathVariable Long id) {
        AnomalyRule r = new AnomalyRule();
        r.setId(id);
        return r;
    }

    @GetMapping("/active")
    public List<AnomalyRule> active() {
        return List.of();
    }
}
