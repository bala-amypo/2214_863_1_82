package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.model.AnomalyFlagRecord;
import com.example.demo.service.AnomalyFlagService;

@RestController
@RequestMapping("/api/anomalies")
public class AnomalyFlagController {

    private final AnomalyFlagService anomalyFlagService;

    public AnomalyFlagController(AnomalyFlagService anomalyFlagService) {
        this.anomalyFlagService = anomalyFlagService;
    }

    @PostMapping
    public AnomalyFlagRecord create(@RequestBody AnomalyFlagRecord record) {
        return anomalyFlagService.createFlag(record);
    }

    @GetMapping
    public List<AnomalyFlagRecord> getAll() {
        return anomalyFlagService.getAllFlags();
    }

    @GetMapping("/employee/{employeeId}")
    public List<AnomalyFlagRecord> byEmployee(@PathVariable Long employeeId) {
        return anomalyFlagService.getFlagsByEmployeeId(employeeId);
    }

    @GetMapping("/metric/{metricId}")
    public List<AnomalyFlagRecord> byMetric(@PathVariable Long metricId) {
        return anomalyFlagService.getFlagsByMetricId(metricId);
    }

    @PutMapping("/{id}/resolve")
    public AnomalyFlagRecord resolve(@PathVariable Long id) {
        return anomalyFlagService.resolveFlag(id);
    }
}
