package com.example.demo.controller;

import com.example.demo.dto.AnomalyFlagDto;
import com.example.demo.model.AnomalyFlagRecord;
import com.example.demo.model.EmployeeProfile;
import com.example.demo.model.ProductivityMetricRecord;
import com.example.demo.service.AnomalyFlagService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/anomalies")
public class AnomalyFlagController {

    private final AnomalyFlagService anomalyFlagService;

    public AnomalyFlagController(AnomalyFlagService anomalyFlagService) {
        this.anomalyFlagService = anomalyFlagService;
    }

    @PostMapping
    public ResponseEntity<AnomalyFlagDto> createFlag(
            @RequestBody AnomalyFlagDto dto
    ) {
        AnomalyFlagRecord saved =
                anomalyFlagService.flagAnomaly(toEntity(dto));
        return ResponseEntity.ok(toDto(saved));
    }

    @PutMapping("/{id}/resolve")
    public ResponseEntity<AnomalyFlagDto> resolveFlag(
            @PathVariable Long id
    ) {
        AnomalyFlagRecord resolved =
                anomalyFlagService.resolveFlag(id);
        return ResponseEntity.ok(toDto(resolved));
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<AnomalyFlagDto>> getByEmployee(
            @PathVariable Long employeeId
    ) {
        List<AnomalyFlagDto> list =
                anomalyFlagService.getFlagsByEmployee(employeeId)
                        .stream()
                        .map(this::toDto)
                        .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @GetMapping("/metric/{metricId}")
    public ResponseEntity<List<AnomalyFlagDto>> getByMetric(
            @PathVariable Long metricId
    ) {
        List<AnomalyFlagDto> list =
                anomalyFlagService.getFlagsByMetric(metricId)
                        .stream()
                        .map(this::toDto)
                        .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @GetMapping
    public ResponseEntity<List<AnomalyFlagDto>> getAll() {
        List<AnomalyFlagDto> list =
                anomalyFlagService.getAllFlags()
                        .stream()
                        .map(this::toDto)
                        .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    /* ---------- MAPPERS ---------- */

    private AnomalyFlagRecord toEntity(AnomalyFlagDto dto) {

        AnomalyFlagRecord flag = new AnomalyFlagRecord();
        flag.setRuleCode(dto.getRuleCode());
        flag.setSeverity(dto.getSeverity());
        flag.setDetails(dto.getDetails());
        flag.setResolved(dto.getResolved());

        if (dto.getEmployeeId() != null) {
            EmployeeProfile e = new EmployeeProfile();
            e.setId(dto.getEmployeeId());
            flag.setEmployee(e);
        }

        if (dto.getMetricId() != null) {
            ProductivityMetricRecord m = new ProductivityMetricRecord();
            m.setId(dto.getMetricId());
            flag.setMetric(m);
        }

        return flag;
    }

    private AnomalyFlagDto toDto(AnomalyFlagRecord flag) {

        AnomalyFlagDto dto = new AnomalyFlagDto();
        dto.setId(flag.getId());
        dto.setEmployeeId(
                flag.getEmployee() != null
                        ? flag.getEmployee().getId()
                        : null
        );
        dto.setMetricId(
                flag.getMetric() != null
                        ? flag.getMetric().getId()
                        : null
        );
        dto.setRuleCode(flag.getRuleCode());
        dto.setSeverity(flag.getSeverity());
        dto.setDetails(flag.getDetails());
        dto.setFlaggedAt(flag.getFlaggedAt());
        dto.setResolved(flag.getResolved());

        return dto;
    }
}
