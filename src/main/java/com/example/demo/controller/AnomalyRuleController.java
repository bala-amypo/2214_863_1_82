package com.example.demo.controller;

import com.example.demo.dto.AnomalyRuleDto;
import com.example.demo.model.AnomalyRule;
import com.example.demo.service.AnomalyRuleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/anomaly-rules")
public class AnomalyRuleController {

    private final AnomalyRuleService anomalyRuleService;

    public AnomalyRuleController(AnomalyRuleService anomalyRuleService) {
        this.anomalyRuleService = anomalyRuleService;
    }

    @PostMapping
    public ResponseEntity<AnomalyRuleDto> createRule(@RequestBody AnomalyRuleDto ruleDto) {
        AnomalyRule rule = new AnomalyRule(ruleDto.getMetricName(), ruleDto.getThreshold(), ruleDto.getComparisonOperator());
        rule.setActive(ruleDto.getActive());
        AnomalyRule created = anomalyRuleService.createRule(rule);
        AnomalyRuleDto response = new AnomalyRuleDto(created.getMetricName(), created.getThreshold(), created.getComparisonOperator(), created.getActive());
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnomalyRuleDto> updateRule(@PathVariable Long id, @RequestBody AnomalyRuleDto ruleDto) {
        // Update logic would go here
        return ResponseEntity.ok(ruleDto);
    }

    @GetMapping("/active")
    public ResponseEntity<List<AnomalyRuleDto>> getActiveRules() {
        List<AnomalyRule> rules = anomalyRuleService.getActiveRules();
        List<AnomalyRuleDto> response = rules.stream()
            .map(r -> new AnomalyRuleDto(r.getMetricName(), r.getThreshold(), r.getComparisonOperator(), r.getActive()))
            .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnomalyRuleDto> getRule(@PathVariable Long id) {
        AnomalyRule rule = anomalyRuleService.getRuleById(id);
        AnomalyRuleDto response = new AnomalyRuleDto(rule.getMetricName(), rule.getThreshold(), rule.getComparisonOperator(), rule.getActive());
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<AnomalyRuleDto>> getAllRules() {
        // Get all rules logic would go here
        return ResponseEntity.ok(List.of());
    }
}