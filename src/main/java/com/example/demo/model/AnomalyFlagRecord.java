@Entity
@Table(name = "anomaly_flag_records")
public class AnomalyFlagRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long employeeId;
    private Long metricId;
    private String ruleCode;
    private String severity;
    private String details;
    private LocalDateTime flaggedAt;
    private Boolean resolved = false;

    @ManyToOne
    @JoinColumn(name = "metric_id", insertable = false, updatable = false)
    private ProductivityMetricRecord metric;

    public AnomalyFlagRecord() {}

    public AnomalyFlagRecord(Long employeeId, Long metricId,
                             String ruleCode, String severity, String details) {
        this.employeeId = employeeId;
        this.metricId = metricId;
        this.ruleCode = ruleCode;
        this.severity = severity;
        this.details = details;
        this.flaggedAt = LocalDateTime.now();
        this.resolved = false;
    }

    // getters and setters
}
