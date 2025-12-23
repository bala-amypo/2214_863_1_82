package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

import com.example.demo.model.ProductivityMetricRecord;
import com.example.demo.service.ProductivityMetricService;

@RestController
@RequestMapping("/api/metrics")
public class ProductivityMetricController {

    private final ProductivityMetricService service;

    public ProductivityMetricController(
            ProductivityMetricService service) {
        this.service = service;
    }

    // ✅ POST /api/metrics
    @PostMapping
    public ProductivityMetricRecord createMetric(
            @RequestBody ProductivityMetricRecord metric) {

        return service.createMetric(metric);
    }

    // ✅ PUT /api/metrics/{id}
    @PutMapping("/{id}")
    public ProductivityMetricRecord updateMetric(
            @PathVariable Long id,
            @RequestBody ProductivityMetricRecord metric) {

        return service.updateMetric(id, metric);
    }
}
