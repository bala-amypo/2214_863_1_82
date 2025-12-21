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
    public AnomalyFlagRecord createFlag(@RequestBody AnomalyFlagRecord flag) {
        return anomalyFlagService.create(flag);
    }

    @GetMapping("/unresolved")
    public List<AnomalyFlagRecord> getUnresolvedFlags() {
        return anomalyFlagService.getUnresolved();
    }
}
