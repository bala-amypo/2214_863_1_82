package com.example.demo.controller;

import com.example.demo.model.AnomalyFlagRecord;
import com.example.demo.service.AnomalyFlagService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/anomalies")
public class AnomalyFlagController {
    
    private final AnomalyFlagService anomalyFlagService;
    
    public AnomalyFlagController(AnomalyFlagService anomalyFlagService) {
        this.anomalyFlagService = anomalyFlagService;
    }
    
    @PostMapping
    public AnomalyFlagRecord flagAnomaly(@RequestBody AnomalyFlagRecord flag) {
        return anomalyFlagService.flagAnomaly(flag);
    }
    
    @PutMapping("/{id}/resolve")
    public AnomalyFlagRecord resolveFlag(@PathVariable Long id) {
        return anomalyFlagService.resolveFlag(id);
    }
    
    @GetMapping("/employee/{employeeId}")
    public List<AnomalyFlagRecord> getFlagsByEmployee(@PathVariable Long employeeId) {
        return anomalyFlagService.getFlagsByEmployee(employeeId);
    }
    
    @GetMapping("/metric/{metricId}")
    public List<AnomalyFlagRecord> getFlagsByMetric(@PathVariable Long metricId) {
        return anomalyFlagService.getFlagsByMetric(metricId);
    }
    
    @GetMapping
    public List<AnomalyFlagRecord> getAllFlags() {
        return anomalyFlagService.getAllFlags();
    }
}