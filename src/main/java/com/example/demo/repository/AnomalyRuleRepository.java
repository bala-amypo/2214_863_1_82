package com.example.demo.repository;

import com.example.demo.model.AnomalyRule;
import java.util.List;

public interface AnomalyRuleRepository {
    List<AnomalyRule> findByActiveTrue();
}
