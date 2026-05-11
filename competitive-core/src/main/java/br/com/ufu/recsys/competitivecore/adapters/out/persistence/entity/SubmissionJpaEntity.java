package br.com.ufu.recsys.competitivecore.adapters.out.persistence.entity;

import br.com.ufu.recsys.competitivecore.domain.model.Verdict;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "submissions", indexes = {
        @Index(name = "idx_submission_student", columnList = "student_id"),
        @Index(name = "idx_submission_problem", columnList = "problem_id")
})
public class SubmissionJpaEntity {

    @Id
    private UUID id;

    @Column(name = "student_id", nullable = false)
    private UUID studentId;

    @Column(name = "problem_id")
    private UUID problemId;

    @Enumerated(EnumType.STRING)
    private Verdict verdict;

    @Column(name = "execution_time_ms")
    private Integer executionTimeMs;

    @Column(name = "submitted_at")
    private LocalDateTime submittedAt;

    protected SubmissionJpaEntity() {}

    public SubmissionJpaEntity(UUID id, UUID studentId, UUID problemId, Verdict verdict, Integer executionTimeMs, LocalDateTime submittedAt) {
        this.id = id;
        this.studentId = studentId;
        this.problemId = problemId;
        this.verdict = verdict;
        this.executionTimeMs = executionTimeMs;
        this.submittedAt = submittedAt;
    }

    public UUID getId() { return id; }
    public UUID getStudentId() { return studentId; }
    public UUID getProblemId() { return problemId; }
    public Verdict getVerdict() { return verdict; }
    public Integer getExecutionTimeMs() { return executionTimeMs; }
    public LocalDateTime getSubmittedAt() { return submittedAt; }
}