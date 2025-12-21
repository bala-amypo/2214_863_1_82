@Override
public List<ProductivityMetricRecord> getAllMetrics() {
    return repository.findAll();
}

@Override
public Optional<ProductivityMetricRecord> getMetricById(Long id) {
    return repository.findById(id);
}
