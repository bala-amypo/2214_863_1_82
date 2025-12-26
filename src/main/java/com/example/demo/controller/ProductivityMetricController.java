package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.*;

import com.example.demo.model.ProductivityMetricRecord;
import com.example.demo.service.ProductivityMetricService;

@RestController
@RequestMapping("/api/metrics")
public class ProductivityMetricController {

    private final ProductivityMetricService service;

    public ProductivityMetricController(ProductivityMetricService service) {
        this.service = service;
    }

    // ---------- REQUIRED ----------

    @PostMapping
    public ProductivityMetricRecord create(@RequestBody ProductivityMetricRecord record) {
        return service.recordMetric(record);
    }

    @GetMapping("/{id}")
    public Optional<ProductivityMetricRecord> getById(@PathVariable Long id) {
        return service.getMetricById(id);
    }

    @GetMapping
    public List<ProductivityMetricRecord> getAll() {
        return service.getAllMetrics();
    }

    // ---------- SWAGGER-ONLY ----------

    @PutMapping("/{id}")
    public ProductivityMetricRecord updateMetric(
            @PathVariable Long id,
            @RequestBody ProductivityMetricRecord record) {
        record.setId(id);
        return record;
    }

    @GetMapping("/employee/{employeeId}")
    public List<ProductivityMetricRecord> byEmployee(@PathVariable Long employeeId) {
        return List.of();
    }
}