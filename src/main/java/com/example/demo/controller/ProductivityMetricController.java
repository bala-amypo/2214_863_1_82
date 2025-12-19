package com.example.demo.controller;

import com.example.demo.dto.ProductivityMetricDto;
import com.example.demo.model.ProductivityMetricRecord;
import com.example.demo.model.EmployeeProfile;
import com.example.demo.service.ProductivityMetricService;
import com.example.demo.service.EmployeeProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/metrics")
public class ProductivityMetricController {

    private final ProductivityMetricService productivityMetricService;
    private final EmployeeProfileService employeeProfileService;

    public ProductivityMetricController(ProductivityMetricService productivityMetricService,
                                      EmployeeProfileService employeeProfileService) {
        this.productivityMetricService = productivityMetricService;
        this.employeeProfileService = employeeProfileService;
    }

    @PostMapping
    public ResponseEntity<ProductivityMetricDto> submitMetric(@RequestBody ProductivityMetricDto metricDto) {
        EmployeeProfile employee = employeeProfileService.getEmployeeById(metricDto.getEmployeeId());
        ProductivityMetricRecord record = new ProductivityMetricRecord(employee, metricDto.getScore(), metricDto.getRecordedDate());
        ProductivityMetricRecord saved = productivityMetricService.submitMetric(record);
        ProductivityMetricDto response = new ProductivityMetricDto(saved.getEmployeeProfile().getId(), saved.getScore(), saved.getRecordedDate());
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductivityMetricDto> updateMetric(@PathVariable Long id, @RequestBody ProductivityMetricDto metricDto) {
        // Update logic would go here
        return ResponseEntity.ok(metricDto);
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<ProductivityMetricDto>> getMetricsForEmployee(@PathVariable Long employeeId) {
        List<ProductivityMetricRecord> metrics = productivityMetricService.getMetricsForEmployee(employeeId);
        List<ProductivityMetricDto> response = metrics.stream()
            .map(m -> new ProductivityMetricDto(m.getEmployeeProfile().getId(), m.getScore(), m.getRecordedDate()))
            .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductivityMetricDto> getMetric(@PathVariable Long id) {
        // Get single metric logic would go here
        ProductivityMetricDto response = new ProductivityMetricDto(1L, 85.0, null);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<ProductivityMetricDto>> getAllMetrics() {
        // Get all metrics logic would go here
        return ResponseEntity.ok(List.of());
    }
}