package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.model.TeamSummaryRecord;
import com.example.demo.service.TeamSummaryService;

@RestController
@RequestMapping("/api/team-summaries")
public class TeamSummaryController {

    private final TeamSummaryService teamSummaryService;

    public TeamSummaryController(TeamSummaryService teamSummaryService) {
        this.teamSummaryService = teamSummaryService;
    }

    @PostMapping("/generate")
    public TeamSummaryRecord generateSummary(
            @RequestParam String teamName,
            @RequestParam String date) {

        return teamSummaryService.generateSummary(
                teamName,
                LocalDate.parse(date)
        );
    }

    @GetMapping("/team/{teamName}")
    public List<TeamSummaryRecord> getByTeam(@PathVariable String teamName) {
        return teamSummaryService.getSummariesByTeam(teamName);
    }

    @GetMapping
    public List<TeamSummaryRecord> getAll() {
        return teamSummaryService.getAllSummaries();
    }

    @GetMapping("/{id}")
    public TeamSummaryRecord getById(@PathVariable Long id) {
        return teamSummaryService.getAllSummaries()
                .stream()
                .filter(s -> s.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
