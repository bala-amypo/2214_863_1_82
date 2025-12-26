package com.example.demo.controller;

import com.example.demo.model.AnomalyFlagRecord;
import com.example.demo.repository.AnomalyFlagRecordRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/anomalies")
public class AnomalyFlagController {

    private final AnomalyFlagRecordRepository anomalyFlagRepository;

    public AnomalyFlagController(AnomalyFlagRecordRepository anomalyFlagRepository) {
        this.anomalyFlagRepository = anomalyFlagRepository;
    }

    // ================= CREATE ANOMALY =================
    @PostMapping
    public AnomalyFlagRecord create(@RequestBody AnomalyFlagRecord request) {

        AnomalyFlagRecord flag = new AnomalyFlagRecord();
        flag.setMetricId(request.getMetricId());
        flag.setRuleCode(request.getRuleCode());
        flag.setSeverity(request.getSeverity());
        flag.setResolved(request.getResolved());
        flag.setDetails(request.getDetails());

        return anomalyFlagRepository.save(flag);
    }

    // ================= GET ALL =================
    @GetMapping
    public List<AnomalyFlagRecord> getAll() {
        return anomalyFlagRepository.findAll();
    }

    // ================= GET BY METRIC =================
    @GetMapping("/metric/{metricId}")
    public List<AnomalyFlagRecord> getByMetric(@PathVariable Long metricId) {
        return anomalyFlagRepository.findByMetricId(metricId);
    }

    // ================= RESOLVE ANOMALY =================
    @PutMapping("/{id}/resolve")
    public AnomalyFlagRecord resolve(@PathVariable Long id) {

        AnomalyFlagRecord flag = anomalyFlagRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Anomaly not found"));

        // ONLY update resolved flag
        flag.setResolved(true);

        return anomalyFlagRepository.save(flag);
    }
}
