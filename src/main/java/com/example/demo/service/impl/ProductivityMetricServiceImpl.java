@Override
public ProductivityMetricRecord createMetric(ProductivityMetricRecord metric) {

    repository.findByEmployeeIdAndDate(
            metric.getEmployeeId(), metric.getDate()
    ).ifPresent(m -> {
        throw new IllegalStateException(
                "Metric already exists for this employee and date");
    });

    Double score = ProductivityCalculator.computeScore(metric);
    metric.setProductivityScore(score);

    return repository.save(metric);
}
