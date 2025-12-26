package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.TeamSummaryRecord;
import com.example.demo.repository.TeamSummaryRecordRepository;
import com.example.demo.service.TeamSummaryService;

@Service
public class TeamSummaryServiceImpl implements TeamSummaryService {

    private final TeamSummaryRecordRepository repo;

    public TeamSummaryServiceImpl(TeamSummaryRecordRepository repo) {
        this.repo = repo;
    }

    @Override
    public TeamSummaryRecord generateSummary(String teamName) {
        TeamSummaryRecord rec = new TeamSummaryRecord();
        rec.setTeamName(teamName);
        return repo.save(rec);
    }

    // 🔹 Added (upgrade)
    @Override
    public List<TeamSummaryRecord> getAllSummaries() {
        return repo.findAll();
    }

    @Override
    public TeamSummaryRecord getById(Long id) {
        return repo.findById(id).orElseThrow();
    }
}
