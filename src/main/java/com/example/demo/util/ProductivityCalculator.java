package com.example.demo.util;

import java.util.List;

public class ProductivityCalculator {

    public static double calculateAverage(List<Double> scores) {
        if (scores == null || scores.isEmpty()) {
            return 0.0;
        }
        
        double sum = 0.0;
        for (Double score : scores) {
            sum += score;
        }
        
        return sum / scores.size();
    }

    public static double computeScore(double hours, int tasks, int meetings) {
        double score = (hours * 10) + (tasks * 5) + (meetings * 2);
        return Math.max(0.0, Math.min(100.0, score));
    }
}