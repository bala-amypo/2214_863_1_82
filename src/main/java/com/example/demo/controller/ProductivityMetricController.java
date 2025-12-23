package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

import com.example.demo.model.ProductivityMetricRecord;
import com.example.demo.service.ProductivityMetricService;

@RestController
@RequestMapping("/api/metrics")
public class ProductivityMetricController {

    private final ProductivityMetricService productivityMetricService;

    public ProductivityMetricController(ProductivityMetricService productivityMetricService) {
        this.productivityMetricService = productivityMetricService;
    }

    @PutMapping("/{id}")
    public ProductivityMetricRecord updateMetric(
            @PathVariable Long id,
            @RequestBody ProductivityMetricRecord metric) {

        return productivityMetricService.updateMetric(id, metric);
    }
}
