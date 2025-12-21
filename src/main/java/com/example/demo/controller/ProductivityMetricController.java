package com.example.demo.controller;

import com.example.demo.model.ProductivityMetricRecord;
import com.example.demo.service.ProductivityService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/metrics")
public class ProductivityMetricController {

    private final ProductivityService productivityService;

    public ProductivityMetricController(ProductivityService productivityService) {
        this.productivityService = productivityService;
    }

    @PostMapping
    public ProductivityMetricRecord submitMetric(@RequestBody ProductivityMetricRecord record) {
        return productivityService.submit(record);
    }

    @GetMapping("/employee/{employeeId}")
    public List<ProductivityMetricRecord> getMetricsByEmployee(
            @PathVariable Long employeeId) {
        return productivityService.getByEmployee(employeeId);
    }
}
