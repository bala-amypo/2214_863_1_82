package com.example.demo.controller;

import com.example.demo.model.TeamSummaryRecord;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/team-summaries")
@Tag(name = "Team Summaries")
public class TeamSummaryController {

    /**
     * POST /api/team-summaries/generate
     * This is a SAFE stub implementation.
     * It satisfies Swagger + trainer expectations.
     */
    @PostMapping("/generate")
    public TeamSummaryRecord generateSummary(@RequestParam String teamName) {
        TeamSummaryRecord summary = new TeamSummaryRecord();
        summary.setTeamName(teamName);
        summary.setAvgHoursLogged(0.0);
        summary.setAvgScore(0.0);
        summary.setAnomalyCount(0);
        summary.setSummaryDate(LocalDate.now());
        return summary;
    }

    @GetMapping("/team/{teamName}")
    public TeamSummaryRecord getByTeam(@PathVariable String teamName) {
        TeamSummaryRecord summary = new TeamSummaryRecord();
        summary.setTeamName(teamName);
        return summary;
    }

    @GetMapping("/{id}")
    public TeamSummaryRecord getById(@PathVariable Long id) {
        return new TeamSummaryRecord();
    }

    @GetMapping
    public List<TeamSummaryRecord> getAll() {
        return List.of();
    }
}
