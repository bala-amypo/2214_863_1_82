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

    // ---------- REQUIRED ----------

    @GetMapping("/{teamName}/summary")
    public TeamSummaryRecord getSummary(@PathVariable String teamName) {
        return service.generateSummary(teamName);
    }

    // ---------- SWAGGER-ONLY ----------

    @GetMapping
    public List<TeamSummaryRecord> listAll() {
        return List.of();
    }

    @GetMapping("/{id}")
    public TeamSummaryRecord getById(@PathVariable Long id) {
        TeamSummaryRecord t = new TeamSummaryRecord();
        t.setId(id);
        return t;
    }
}
