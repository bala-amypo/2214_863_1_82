package com.example.demo.util;

public class ProductivityCalculator {

    public static double computeScore(double hours, int tasks, int meetings) {
        double score = (hours * 10) + (tasks * 5) + (meetings * 2);

        if (score < 0.0) {
            return 0.0;
        }
        if (score > 100.0) {
            return 100.0;
        }
        return score;
    }
}
