package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.model.TeamSummaryRecord;
import com.example.demo.service.TeamSummaryService;

@RestController
@RequestMapping("/api/teams")
public class TeamSummaryController {

    private final TeamSummaryService service;

    public TeamSummaryController(TeamSummaryService service) {
        this.service = service;
    }

    // ---------------- BASIC ENDPOINT ----------------

    @GetMapping("/{teamName}/summary")
    public TeamSummaryRecord getSummary(@PathVariable String teamName) {
        return service.generateSummary(teamName);
    }

    // ----------- ADDITIONAL ENDPOINTS (Swagger completeness) -----------

    @PostMapping("/generate")
    public TeamSummaryRecord generateSummary(
            @RequestParam String teamName) {
        return service.generateSummary(teamName);
    }

    @GetMapping
    public List<TeamSummaryRecord> getAllSummaries() {
        return service.getAllSummaries();
    }

    @GetMapping("/{id}")
    public TeamSummaryRecord getById(@PathVariable Long id) {
        return service.getById(id);
    }
}
