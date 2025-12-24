package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
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

    // ---------------- CREATE ----------------
    @PostMapping
    public ResponseEntity<AnomalyFlagRecord> create(
            @RequestBody AnomalyFlagRecord flag) {

        return ResponseEntity.ok(
                anomalyFlagService.createFlag(flag)
        );
    }

    // ---------------- READ ----------------
    @GetMapping
    public ResponseEntity<List<AnomalyFlagRecord>> getAll() {
        return ResponseEntity.ok(
                anomalyFlagService.getAllFlags()
        );
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<AnomalyFlagRecord>> getByEmployee(
            @PathVariable Long employeeId) {

        return ResponseEntity.ok(
                anomalyFlagService.getFlagsByEmployeeId(employeeId)
        );
    }

    @GetMapping("/metric/{metricId}")
    public ResponseEntity<List<AnomalyFlagRecord>> getByMetric(
            @PathVariable Long metricId) {

        return ResponseEntity.ok(
                anomalyFlagService.getFlagsByMetricId(metricId)
        );
    }

    // ---------------- RESOLVE ----------------
    @PutMapping("/{id}/resolve")
    public ResponseEntity<AnomalyFlagRecord> resolve(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                anomalyFlagService.resolveFlag(id)
        );
    }
}
