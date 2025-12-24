package com.example.demo.util;

import com.example.demo.model.ProductivityMetricRecord;

public final class ProductivityCalculator {

    private ProductivityCalculator() {
        // utility class - prevent instantiation
    }

    /**
     * Computes productivity score using the formula:
     * (hours * 10) + (tasks * 5) + (meetings * 2)
     * Result is clamped between 0.0 and 100.0
     */
    public static double computeScore(ProductivityMetricRecord metric) {

        double hours = metric.getHoursLogged() != null && metric.getHoursLogged() > 0
                ? metric.getHoursLogged()
                : 0.0;

        int tasks = metric.getTasksCompleted() != null && metric.getTasksCompleted() > 0
                ? metric.getTasksCompleted()
                : 0;

        int meetings = metric.getMeetingsAttended() != null && metric.getMeetingsAttended() > 0
                ? metric.getMeetingsAttended()
                : 0;

        double score = (hours * 10)
                + (tasks * 5)
                + (meetings * 2);

        if (score < 0.0) {
            score = 0.0;
        } else if (score > 100.0) {
            score = 100.0;
        }

        return score;
    }
}
