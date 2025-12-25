package com.example.demo.controller;

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

    @GetMapping("/{teamName}/summary")
    public TeamSummaryRecord getSummary(@PathVariable String teamName) {
        return service.generateSummary(teamName);
    }
}
