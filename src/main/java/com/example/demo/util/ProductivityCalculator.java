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
}