package com.example.demo.controller;

import com.example.demo.model.TeamSummaryRecord;
import com.example.demo.service.TeamSummaryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/team-summaries")
public class TeamSummaryController {

    private final TeamSummaryService teamSummaryService;

    public TeamSummaryController(TeamSummaryService teamSummaryService) {
        this.teamSummaryService = teamSummaryService;
    }

    @PostMapping
    public TeamSummaryRecord createSummary(@RequestBody TeamSummaryRecord summary) {
        return teamSummaryService.create(summary);
    }

    @GetMapping
    public List<TeamSummaryRecord> getAllSummaries() {
        return teamSummaryService.getAll();
    }
}
