package com.example.demo.util;

public class ProductivityCalculator {

    public static double computeScore(double hours, int tasks, int meetings) {
        hours = Math.max(0, hours);
        tasks = Math.max(0, tasks);
        meetings = Math.max(0, meetings);

        double score = (hours * 10) + (tasks * 5) + (meetings * 2);
        return Math.min(100.0, Math.max(0.0, score));
    }
}
