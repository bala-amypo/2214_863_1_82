package com.example.demo.util;

import com.example.demo.model.ProductivityMetricRecord;

public class ProductivityCalculator {

    public static Double computeScore(ProductivityMetricRecord record) {

        double score =
                (record.getHoursLogged() * 10) +
                (record.getTasksCompleted() * 5) +
                (record.getMeetingsAttended() * 2);

        if (score > 100) return 100.0;
        if (score < 0) return 0.0;

        return score;
    }
}
