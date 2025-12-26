package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.model.AnomalyFlagRecord;
import com.example.demo.service.AnomalyFlagService;

@RestController
@RequestMapping("/api/anomalies")
public class AnomalyFlagController {

    private final AnomalyFlagService service;

    public AnomalyFlagController(AnomalyFlagService service) {
        this.service = service;
    }

    // ---------------- BASIC ENDPOINTS ----------------

    @PostMapping
    public AnomalyFlagRecord create(@RequestBody AnomalyFlagRecord record) {
        return service.flagAnomaly(record);
    }

    @GetMapping
    public List<AnomalyFlagRecord> getAll() {
        return service.getAllFlags();
    }

    // ----------- ADDITIONAL ENDPOINTS (Swagger completeness) -----------

    @PutMapping("/{id}/resolve")
    public AnomalyFlagRecord resolveFlag(@PathVariable Long id) {
        return service.resolveFlag(id);
    }

    @GetMapping("/employee/{employeeId}")
    public List<AnomalyFlagRecord> getByEmployee(@PathVariable Long employeeId) {
        return service.getByEmployee(employeeId);
    }

    @GetMapping("/metric/{metricId}")
    public List<AnomalyFlagRecord> getByMetric(@PathVariable Long metricId) {
        return service.getByMetric(metricId);
    }
}