package com.example.demo.service.impl;

import org.springframework.stereotype.Service;
import java.util.List;
import com.example.demo.model.TeamSummaryRecord;
import com.example.demo.service.TeamSummaryService;

@Service
public class TeamSummaryServiceImpl implements TeamSummaryService {

    @Override
    public TeamSummaryRecord generateSummary(String teamName) {
        TeamSummaryRecord record = new TeamSummaryRecord();
        record.setTeamName(teamName);
        return record;
    }
    @Override
public List<TeamSummaryRecord> getAllSummaries() {
    return repo.findAll();
}

@Override
public TeamSummaryRecord getById(Long id) {
    return repo.findById(id).orElseThrow();
}

}
