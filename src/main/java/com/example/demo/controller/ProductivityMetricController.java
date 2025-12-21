package com.example.demo.controller;

import com.example.demo.model.ProductivityMetricRecord;
import com.example.demo.service.ProductivityMetricService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/metrics")
public class ProductivityMetricController {

    private final ProductivityMetricService service;

    public ProductivityMetricController(ProductivityMetricService service) {
        this.service = service;
    }

    @PostMapping
    public ProductivityMetricRecord recordMetric(
            @RequestBody ProductivityMetricRecord record) {
        return service.recordMetric(record);
    }

    @GetMapping("/employee/{employeeId}")
    public List<ProductivityMetricRecord> getByEmployee(
            @PathVariable Long employeeId) {
        return service.getMetricsByEmployee(employeeId);
    }
}
