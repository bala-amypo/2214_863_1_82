package com.example.demo.controller;

import com.example.demo.model.AnomalyFlagRecord;
import com.example.demo.service.AnomalyFlagService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/anomalies")
public class AnomalyFlagController {

    private final AnomalyFlagService service;

    public AnomalyFlagController(AnomalyFlagService service) {
        this.service = service;
    }

    @PostMapping
    public AnomalyFlagRecord flag(@RequestBody AnomalyFlagRecord flag) {
        return service.flagAnomaly(flag);
    }

    @GetMapping
    public List<AnomalyFlagRecord> getAll() {
        return service.getAllFlags();
    }
}
