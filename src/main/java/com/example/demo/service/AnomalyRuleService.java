public interface AnomalyRuleService {

    AnomalyRule createRule(AnomalyRule rule);

    List<AnomalyRule> getAllRules();

    AnomalyRule getRuleById(Long id);

    AnomalyRule updateRule(Long id, AnomalyRule rule);

    void deleteRule(Long id);

    boolean evaluateRule(
        AnomalyRule rule,
        Integer productivityScore,
        Integer hoursLogged,
        Integer meetingsAttended
    );
}
