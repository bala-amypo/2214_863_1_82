package com.example.demo.controller;

import com.example.demo.dto.ProductivityMetricDto;
import com.example.demo.model.EmployeeProfile;
import com.example.demo.model.ProductivityMetricRecord;
import com.example.demo.service.ProductivityMetricService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/metrics")
public class ProductivityMetricController {

    private final ProductivityMetricService productivityMetricService;

    public ProductivityMetricController(ProductivityMetricService productivityMetricService) {
        this.productivityMetricService = productivityMetricService;
    }

    @PostMapping
    public ResponseEntity<ProductivityMetricDto> recordMetric(
            @RequestBody ProductivityMetricDto dto
    ) {
        ProductivityMetricRecord record = toEntity(dto);
        ProductivityMetricRecord saved =
                productivityMetricService.recordMetric(record);
        return ResponseEntity.ok(toDto(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductivityMetricDto> updateMetric(
            @PathVariable Long id,
            @RequestBody ProductivityMetricDto dto
    ) {
        ProductivityMetricRecord updated =
                productivityMetricService.updateMetric(id, toEntity(dto));
        return ResponseEntity.ok(toDto(updated));
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<ProductivityMetricDto>> getByEmployee(
            @PathVariable Long employeeId
    ) {
        List<ProductivityMetricDto> list =
                productivityMetricService.getMetricsByEmployee(employeeId)
                        .stream()
                        .map(this::toDto)
                        .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductivityMetricDto> getById(
            @PathVariable Long id
    ) {
        ProductivityMetricRecord record =
                productivityMetricService.getMetricById(id)
                        .orElseThrow(() ->
                                new RuntimeException("Metric not found"));
        return ResponseEntity.ok(toDto(record));
    }

    @GetMapping
    public ResponseEntity<List<ProductivityMetricDto>> getAll() {
        List<ProductivityMetricDto> list =
                productivityMetricService.getAllMetrics()
                        .stream()
                        .map(this::toDto)
                        .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    /* ---------- MAPPERS ---------- */

    private ProductivityMetricRecord toEntity(ProductivityMetricDto dto) {

        ProductivityMetricRecord record = new ProductivityMetricRecord();
        record.setDate(dto.getDate());
        record.setHoursLogged(dto.getHoursLogged());
        record.setTasksCompleted(dto.getTasksCompleted());
        record.setMeetingsAttended(dto.getMeetingsAttended());
        record.setRawDataJson(dto.getRawDataJson());

        if (dto.getEmployeeId() != null) {
            EmployeeProfile employee = new EmployeeProfile();
            employee.setId(dto.getEmployeeId());
            record.setEmployee(employee);
        }

        return record;
    }

    private ProductivityMetricDto toDto(ProductivityMetricRecord record) {

        ProductivityMetricDto dto = new ProductivityMetricDto();
        dto.setId(record.getId());
        dto.setEmployeeId(
                record.getEmployee() != null
                        ? record.getEmployee().getId()
                        : null
        );
        dto.setDate(record.getDate());
        dto.setHoursLogged(record.getHoursLogged());
        dto.setTasksCompleted(record.getTasksCompleted());
        dto.setMeetingsAttended(record.getMeetingsAttended());
        dto.setProductivityScore(record.getProductivityScore());
        dto.setRawDataJson(record.getRawDataJson());
        dto.setSubmittedAt(record.getSubmittedAt());

        return dto;
    }
}
