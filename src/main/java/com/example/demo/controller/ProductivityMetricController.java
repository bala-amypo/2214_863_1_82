package com.example.demo.controller;

import java.util.List;

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

    // ============== POST =================
    @PostMapping
    public ProductivityMetricRecord createMetric(
            @RequestBody ProductivityMetricRecord metric) {

        return service.createMetric(metric);
    }

    // ============== PUT =================
    @PutMapping("/{id}")
    public ProductivityMetricRecord updateMetric(
            @PathVariable Long id,
            @RequestBody ProductivityMetricRecord metric) {

        return service.updateMetric(id, metric);
    }

    // ============== GET ALL =================
    @GetMapping
    public List<ProductivityMetricRecord> getAllMetrics() {
        return service.getAllMetrics();
    }

    // ============== GET BY ID =================
    @GetMapping("/{id}")
    public ProductivityMetricRecord getMetricById(
            @PathVariable Long id) {

        return service.getMetricById(id);
    }

    // ============== GET BY EMPLOYEE (FIXES 500) =================
    @GetMapping("/employee/{employeeId}")
    public List<ProductivityMetricRecord> getMetricsByEmployee(
            @PathVariable Long employeeId) {

        return service.getMetricsByEmployee(employeeId);
    }
}