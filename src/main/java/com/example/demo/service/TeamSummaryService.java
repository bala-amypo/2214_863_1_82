package com.example.demo.service;

import java.util.List;

import com.example.demo.model.TeamSummaryRecord;

public interface TeamSummaryService {

    TeamSummaryRecord generateSummary(String teamName);

    // 🔹 Added (upgrade)
    List<TeamSummaryRecord> getAllSummaries();

    TeamSummaryRecord getById(Long id);
}
