@Entity
@Table(
    name = "team_summary_records",
    uniqueConstraints = @UniqueConstraint(columnNames = {"teamName", "summaryDate"})
)
public class TeamSummaryRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String teamName;
    private LocalDate summaryDate;
    private Double avgHoursLogged;
    private Double avgTasksCompleted;
    private Double avgScore;
    private Integer anomalyCount;
    private LocalDateTime generatedAt;

    public TeamSummaryRecord() {}

    public TeamSummaryRecord(String teamName, LocalDate summaryDate) {
        this.teamName = teamName;
        this.summaryDate = summaryDate;
        this.generatedAt = LocalDateTime.now();
    }

    // getters and setters
}
