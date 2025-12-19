@Entity
@Table(
    name = "anomaly_rules",
    uniqueConstraints = @UniqueConstraint(columnNames = "ruleCode")
)
public class AnomalyRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ruleCode;
    private String description;
    private String thresholdType;
    private Double thresholdValue;
    private Boolean active;

    public AnomalyRule() {}

    public AnomalyRule(String ruleCode, String description,
                       String thresholdType, Double thresholdValue, Boolean active) {
        this.ruleCode = ruleCode;
        this.description = description;
        this.thresholdType = thresholdType;
        this.thresholdValue = thresholdValue;
        this.active = active;
    }

    // getters and setters
}
