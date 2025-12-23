@Override
public ProductivityMetricRecord recordMetric(ProductivityMetricRecord record) {

    double score =
            (record.getHoursLogged() * 10) +
            (record.getTasksCompleted() * 5) +
            (record.getMeetingsAttended() * 2);

    // clamp between 0 and 100
    if (score < 0) score = 0;
    if (score > 100) score = 100;

    record.setProductivityScore(score);

    return repository.save(record);
}
