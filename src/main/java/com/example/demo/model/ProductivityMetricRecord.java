package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(
    name = "productivity_metric_records",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = { "employee_id", "metric_date" })
    }
)
public class ProductivityMetricRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* ---------- RELATIONSHIPS ---------- */

    @ManyToOne(optional = false)
    @JoinColumn(name = "employee_id", nullable = false)
    private EmployeeProfile employee;

    @Column(name = "metric_date", nullable = false)
    private LocalDate date;

    @Column(name = "hours_logged", nullable = false)
    private Double hoursLogged;

    @Column(name = "tasks_completed", nullable = false)
    private Integer tasksCompleted;

    @Column(name = "meetings_attended", nullable = false)
    private Integer meetingsAttended;

    @Column(name = "productivity_score", nullable = false)
    private Double productivityScore;

    @Column(name = "raw_data_json", columnDefinition = "TEXT")
    private String rawDataJson;

    @Column(name = "submitted_at", nullable = false, updatable = false)
    private LocalDateTime submittedAt;

    @OneToMany(mappedBy = "metric", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AnomalyFlagRecord> anomalyFlags;

    /* ---------- CONSTRUCTORS ---------- */

    public ProductivityMetricRecord() {
        // required by JPA
    }

    public ProductivityMetricRecord(
            EmployeeProfile employee,
            LocalDate date,
            Double hoursLogged,
            Integer tasksCompleted,
            Integer meetingsAttended,
            String rawDataJson
    ) {
        this.employee = employee;
        this.date = date;
        this.hoursLogged = hoursLogged;
        this.tasksCompleted = tasksCompleted;
        this.meetingsAttended = meetingsAttended;
        this.rawDataJson = rawDataJson;
    }

    /* ---------- LIFECYCLE ---------- */

    @PrePersist
    protected void onCreate() {
        this.submittedAt = LocalDateTime.now();
        if (this.productivityScore == null) {
            this.productivityScore = 0.0;
        }
    }

    /* ---------- GETTERS & SETTERS ---------- */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EmployeeProfile getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeProfile employee) {
        this.employee = employee;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getHoursLogged() {
        return hoursLogged;
    }

    public void setHoursLogged(Double hoursLogged) {
        this.hoursLogged = hoursLogged;
    }

    public Integer getTasksCompleted() {
        return tasksCompleted;
    }

    public void setTasksCompleted(Integer tasksCompleted) {
        this.tasksCompleted = tasksCompleted;
    }

    public Integer getMeetingsAttended() {
        return meetingsAttended;
    }

    public void setMeetingsAttended(Integer meetingsAttended) {
        this.meetingsAttended = meetingsAttended;
    }

    public Double getProductivityScore() {
        return productivityScore;
    }

    public void setProductivityScore(Double productivityScore) {
        this.productivityScore = productivityScore;
    }

    public String getRawDataJson() {
        return rawDataJson;
    }

    public void setRawDataJson(String rawDataJson) {
        this.rawDataJson = rawDataJson;
    }

    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }

    public List<AnomalyFlagRecord> getAnomalyFlags() {
        return anomalyFlags;
    }

    public void setAnomalyFlags(List<AnomalyFlagRecord> anomalyFlags) {
        this.anomalyFlags = anomalyFlags;
    }
}
