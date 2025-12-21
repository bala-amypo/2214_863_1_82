@Override
public AnomalyRule updateRule(Long id, AnomalyRule updated) {
    AnomalyRule rule = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Rule not found"));

    rule.setRuleCode(updated.getRuleCode());
    rule.setDescription(updated.getDescription());
    rule.setThresholdType(updated.getThresholdType());
    rule.setThresholdValue(updated.getThresholdValue());
    rule.setActive(updated.getActive());

    return repository.save(rule);
}

@Override
public List<AnomalyRule> getAllRules() {
    return repository.findAll();
}
