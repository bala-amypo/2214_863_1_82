package com.example.demo.service;

import com.example.demo.model.TeamSummaryRecord;

public interface TeamSummaryService {

    TeamSummaryRecord generateSummary(String teamName);
}
