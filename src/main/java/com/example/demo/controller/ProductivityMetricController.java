@GetMapping
public List<ProductivityMetricRecord> getAll() {
    return service.getAllMetrics();
}

@GetMapping("/{id}")
public ProductivityMetricRecord getById(@PathVariable Long id) {
    return service.getMetricById(id).orElse(null);
}

@PutMapping("/{id}")
public ProductivityMetricRecord update(
        @PathVariable Long id,
        @RequestBody ProductivityMetricRecord record) {
    return record;
}
