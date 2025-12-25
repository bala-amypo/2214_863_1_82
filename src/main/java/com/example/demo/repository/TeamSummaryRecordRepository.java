package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.TeamSummaryRecord;

@Repository
public interface TeamSummaryRecordRepository
        extends JpaRepository<TeamSummaryRecord, Long> {
}
