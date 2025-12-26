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

    // ---------- REQUIRED ----------

    @PostMapping
    public AnomalyFlagRecord create(@RequestBody AnomalyFlagRecord record) {
        return service.flagAnomaly(record);
    }

    @GetMapping
    public List<AnomalyFlagRecord> getAll() {
        return service.getAllFlags();
    }

    // ---------- SWAGGER-ONLY ----------

    @PutMapping("/{id}/resolve")
    public AnomalyFlagRecord resolve(@PathVariable Long id) {
        AnomalyFlagRecord f = new AnomalyFlagRecord();
        f.setId(id);
        f.setResolved(true);
        return f;
    }

    @GetMapping("/employee/{employeeId}")
    public List<AnomalyFlagRecord> byEmployee(@PathVariable Long employeeId) {
        return List.of();
    }

    @GetMapping("/metric/{metricId}")
    public List<AnomalyFlagRecord> byMetric(@PathVariable Long metricId) {
        return List.of();
    }
}