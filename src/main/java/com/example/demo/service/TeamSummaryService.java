package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.model.TeamSummaryRecord;

public interface TeamSummaryService {

    TeamSummaryRecord generateSummary(String teamName, LocalDate summaryDate);

    List<TeamSummaryRecord> getSummariesByTeam(String teamName);

    List<TeamSummaryRecord> getAllSummaries();
}
