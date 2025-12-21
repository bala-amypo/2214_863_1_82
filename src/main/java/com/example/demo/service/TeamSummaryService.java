package com.example.demo.service;

import com.example.demo.model.TeamSummaryRecord;
import java.util.List;

public interface TeamSummaryService {

    TeamSummaryRecord createSummary(TeamSummaryRecord summary);

    List<TeamSummaryRecord> getAllSummaries();
}
