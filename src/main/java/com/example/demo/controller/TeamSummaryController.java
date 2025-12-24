package com.example.demo.controller;

import com.example.demo.dto.TeamSummaryDto;
import com.example.demo.model.TeamSummaryRecord;
import com.example.demo.service.TeamSummaryService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
    public ResponseEntity<TeamSummaryDto> generateSummary(
            @RequestParam String teamName,
            @RequestParam String summaryDate
    ) {
        TeamSummaryRecord summary =
                teamSummaryService.generateSummary(
                        teamName,
                        LocalDate.parse(summaryDate)
                );
        return ResponseEntity.ok(toDto(summary));
    }

    @GetMapping("/team/{teamName}")
    public ResponseEntity<List<TeamSummaryDto>> getByTeam(
            @PathVariable String teamName
    ) {
        List<TeamSummaryDto> list =
                teamSummaryService.getSummariesByTeam(teamName)
                        .stream()
                        .map(this::toDto)
                        .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @GetMapping
    public ResponseEntity<List<TeamSummaryDto>> getAll() {
        List<TeamSummaryDto> list =
                teamSummaryService.getAllSummaries()
                        .stream()
                        .map(this::toDto)
                        .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    /* ---------- MAPPER ---------- */

    private TeamSummaryDto toDto(TeamSummaryRecord record) {

        TeamSummaryDto dto = new TeamSummaryDto();
        dto.setId(record.getId());
        dto.setTeamName(record.getTeamName());
        dto.setSummaryDate(record.getSummaryDate());
        dto.setAvgHoursLogged(record.getAvgHoursLogged());
        dto.setAvgTasksCompleted(record.getAvgTasksCompleted());
        dto.setAvgScore(record.getAvgScore());
        dto.setAnomalyCount(record.getAnomalyCount());
        dto.setGeneratedAt(record.getGeneratedAt());

        return dto;
    }
}
