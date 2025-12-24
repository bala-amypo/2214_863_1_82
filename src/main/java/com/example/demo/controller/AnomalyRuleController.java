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
    public ResponseEntity<AnomalyRuleDto> createRule(
            @RequestBody AnomalyRuleDto dto
    ) {
        AnomalyRule saved =
                anomalyRuleService.createRule(toEntity(dto));
        return ResponseEntity.ok(toDto(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnomalyRuleDto> updateRule(
            @PathVariable Long id,
            @RequestBody AnomalyRuleDto dto
    ) {
        AnomalyRule updated =
                anomalyRuleService.updateRule(id, toEntity(dto));
        return ResponseEntity.ok(toDto(updated));
    }

    @GetMapping("/active")
    public ResponseEntity<List<AnomalyRuleDto>> getActiveRules() {
        List<AnomalyRuleDto> list =
                anomalyRuleService.getActiveRules()
                        .stream()
                        .map(this::toDto)
                        .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnomalyRuleDto> getRuleById(
            @PathVariable Long id
    ) {
        AnomalyRule rule =
                anomalyRuleService.getAllRules()
                        .stream()
                        .filter(r -> r.getId().equals(id))
                        .findFirst()
                        .orElseThrow(() ->
                                new RuntimeException("Rule not found"));

        return ResponseEntity.ok(toDto(rule));
    }

    @GetMapping
    public ResponseEntity<List<AnomalyRuleDto>> getAllRules() {
        List<AnomalyRuleDto> list =
                anomalyRuleService.getAllRules()
                        .stream()
                        .map(this::toDto)
                        .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    /* ---------- MAPPERS ---------- */

    private AnomalyRule toEntity(AnomalyRuleDto dto) {
        AnomalyRule rule = new AnomalyRule();
        rule.setRuleCode(dto.getRuleCode());
        rule.setDescription(dto.getDescription());
        rule.setThresholdType(dto.getThresholdType());
        rule.setThresholdValue(dto.getThresholdValue());
        rule.setActive(dto.getActive());
        return rule;
    }

    private AnomalyRuleDto toDto(AnomalyRule rule) {
        AnomalyRuleDto dto = new AnomalyRuleDto();
        dto.setId(rule.getId());
        dto.setRuleCode(rule.getRuleCode());
        dto.setDescription(rule.getDescription());
        dto.setThresholdType(rule.getThresholdType());
        dto.setThresholdValue(rule.getThresholdValue());
        dto.setActive(rule.getActive());
        return dto;
    }
}
