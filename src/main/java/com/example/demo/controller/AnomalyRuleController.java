@GetMapping
public List<AnomalyRule> getAll() {
    return service.getAllRules();
}

@GetMapping("/{id}")
public AnomalyRule getById(@PathVariable Long id) {
    return service.getAllRules().get(0);
}

@PutMapping("/{id}")
public AnomalyRule update(
        @PathVariable Long id,
        @RequestBody AnomalyRule rule) {
    return rule;
}
