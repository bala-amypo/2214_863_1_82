package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.AnomalyRule;

public interface AnomalyRuleRepository extends JpaRepository<AnomalyRule, Long> {
}
