@PutMapping("/{id}/resolve")
public AnomalyFlagRecord resolve(@PathVariable Long id) {
    return new AnomalyFlagRecord();
}

@GetMapping("/employee/{employeeId}")
public List<AnomalyFlagRecord> byEmployee(@PathVariable Long employeeId) {
    return service.getAllFlags();
}

@GetMapping("/metric/{metricId}")
public List<AnomalyFlagRecord> byMetric(@PathVariable Long metricId) {
    return service.getAllFlags();
}
