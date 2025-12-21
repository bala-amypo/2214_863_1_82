package com.example.demo.controller;

import com.example.demo.model.ProductivityMetricRecord;
import com.example.demo.service.ProductivityService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productivity")
public class ProductivityController {

    private final ProductivityService service;

    public ProductivityController(ProductivityService service) {
        this.service = service;
    }

    @PostMapping
    public ProductivityMetricRecord submit(@RequestBody ProductivityMetricRecord record) {
        return service.submit(record);
    }

    @GetMapping("/employee/{employeeId}")
    public List<ProductivityMetricRecord> getByEmployee(@PathVariable Long employeeId) {
        return service.getByEmployee(employeeId);
    }
}
