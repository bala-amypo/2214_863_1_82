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

    @PostMapping("/generate")
    public TeamSummaryRecord generate(@RequestParam String teamName,
                                      @RequestParam String date) {
        return new TeamSummaryRecord();
    }

    @GetMapping
    public List<TeamSummaryRecord> getAll() {
        return service.getAllSummaries();
    }

    @GetMapping("/{id}")
    public TeamSummaryRecord getById(@PathVariable Long id) {
        return service.getAllSummaries().get(0);
    }

    @GetMapping("/team/{teamName}")
    public List<TeamSummaryRecord> byTeam(@PathVariable String teamName) {
        return service.getAllSummaries();
    }
}
