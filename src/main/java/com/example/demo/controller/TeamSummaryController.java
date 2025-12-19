package com.example.demo.controller;

import com.example.demo.dto.TeamSummaryDto;
import com.example.demo.model.TeamSummaryRecord;
import com.example.demo.service.TeamSummaryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/team-summaries")
public class TeamSummaryController {

    private final TeamSummaryService teamSummaryService;

    public TeamSummaryController(TeamSummaryService teamSummaryService) {
        this.teamSummaryService = teamSummaryService;
    }

    @PostMapping("/generate")
    public ResponseEntity<TeamSummaryDto> generateSummary(@RequestParam String teamName, @RequestParam String date) {
        TeamSummaryRecord summary = teamSummaryService.generateSummary(teamName);
        TeamSummaryDto response = new TeamSummaryDto(summary.getDepartment(), summary.getAverageScore());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/team/{teamName}")
    public ResponseEntity<List<TeamSummaryDto>> getSummariesByTeam(@PathVariable String teamName) {
        List<TeamSummaryRecord> summaries = teamSummaryService.getAllSummaries();
        List<TeamSummaryDto> response = summaries.stream()
            .filter(s -> teamName.equals(s.getDepartment()))
            .map(s -> new TeamSummaryDto(s.getDepartment(), s.getAverageScore()))
            .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeamSummaryDto> getSummary(@PathVariable Long id) {
        // Get summary by ID logic would go here
        TeamSummaryDto response = new TeamSummaryDto("Engineering", 85.0);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<TeamSummaryDto>> getAllSummaries() {
        List<TeamSummaryRecord> summaries = teamSummaryService.getAllSummaries();
        List<TeamSummaryDto> response = summaries.stream()
            .map(s -> new TeamSummaryDto(s.getDepartment(), s.getAverageScore()))
            .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }
}