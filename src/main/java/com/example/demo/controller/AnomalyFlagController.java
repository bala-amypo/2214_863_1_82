package com.example.demo.controller;

import com.example.demo.dto.AnomalyFlagDto;
import com.example.demo.model.AnomalyFlagRecord;
import com.example.demo.model.EmployeeProfile;
import com.example.demo.model.AnomalyRule;
import com.example.demo.service.AnomalyFlagService;
import com.example.demo.service.EmployeeProfileService;
import com.example.demo.service.AnomalyRuleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/anomalies")
public class AnomalyFlagController {

    private final AnomalyFlagService anomalyFlagService;
    private final EmployeeProfileService employeeProfileService;
    private final AnomalyRuleService anomalyRuleService;

    public AnomalyFlagController(AnomalyFlagService anomalyFlagService,
                               EmployeeProfileService employeeProfileService,
                               AnomalyRuleService anomalyRuleService) {
        this.anomalyFlagService = anomalyFlagService;
        this.employeeProfileService = employeeProfileService;
        this.anomalyRuleService = anomalyRuleService;
    }

    @PostMapping
    public ResponseEntity<AnomalyFlagDto> flagAnomaly(@RequestBody AnomalyFlagDto flagDto) {
        EmployeeProfile employee = employeeProfileService.getEmployeeById(flagDto.getEmployeeId());
        AnomalyRule rule = anomalyRuleService.getRuleById(flagDto.getRuleId());
        
        AnomalyFlagRecord record = new AnomalyFlagRecord(employee, rule, flagDto.getFlaggedValue());
        record.setResolved(flagDto.getResolved());
        AnomalyFlagRecord saved = anomalyFlagService.flagAnomaly(record);
        AnomalyFlagDto response = new AnomalyFlagDto(saved.getEmployeeProfile().getId(), saved.getAnomalyRule().getId(), saved.getFlaggedValue(), saved.getResolved());
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/resolve")
    public ResponseEntity<AnomalyFlagDto> resolveFlag(@PathVariable Long id) {
        // Resolve flag logic would go here
        AnomalyFlagDto response = new AnomalyFlagDto(1L, 1L, 0.0, true);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<AnomalyFlagDto>> getFlagsByEmployee(@PathVariable Long employeeId) {
        // Get flags by employee logic would go here
        return ResponseEntity.ok(List.of());
    }

    @GetMapping("/metric/{metricId}")
    public ResponseEntity<List<AnomalyFlagDto>> getFlagsByMetric(@PathVariable Long metricId) {
        // Get flags by metric logic would go here
        return ResponseEntity.ok(List.of());
    }

    @GetMapping
    public ResponseEntity<List<AnomalyFlagDto>> getAllFlags() {
        List<AnomalyFlagRecord> flags = anomalyFlagService.getUnresolvedFlags();
        List<AnomalyFlagDto> response = flags.stream()
            .map(f -> new AnomalyFlagDto(f.getEmployeeProfile().getId(), f.getAnomalyRule().getId(), f.getFlaggedValue(), f.getResolved()))
            .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }
}