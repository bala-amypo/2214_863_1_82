package com.example.demo.controller;

import com.example.demo.model.TeamSummaryRecord;
import com.example.demo.service.TeamSummaryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/team-summaries")
public class TeamSummaryController {

    private final TeamSummaryService service;

    public TeamSummaryController(TeamSummaryService service) {
        this.service = service;
    }

    @PostMapping
    public TeamSummaryRecord createSummary(
            @RequestBody TeamSummaryRecord summary) {
        return service.createSummary(summary);
    }

    @GetMapping
    public List<TeamSummaryRecord> getAllSummaries() {
        return service.getAllSummaries();
    }
}
