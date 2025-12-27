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

    @GetMapping
    public List<AnomalyFlagRecord> getAll() {
        return anomalyFlagRepository.findAll();
    }

    @GetMapping("/metric/{metricId}")
    public List<AnomalyFlagRecord> getByMetric(@PathVariable Long metricId) {
        return anomalyFlagRepository.findByMetricId(metricId);
    }

    @PutMapping("/{id}/resolve")
    public AnomalyFlagRecord resolve(@PathVariable Long id) {

        AnomalyFlagRecord flag = anomalyFlagRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Anomaly not found"));

        flag.setResolved(true);

        return anomalyFlagRepository.save(flag);
    }
}
