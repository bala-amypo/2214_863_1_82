package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.AnomalyRule;

@Repository
public interface AnomalyRuleRepository extends JpaRepository<AnomalyRule, Long> {

    List<AnomalyRule> findByActiveTrue();
}
