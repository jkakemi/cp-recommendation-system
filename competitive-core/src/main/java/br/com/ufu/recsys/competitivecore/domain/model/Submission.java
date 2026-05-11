package br.com.ufu.recsys.competitivecore.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Submission {
    private final UUID id;
    private final UUID studentId;
    private UUID problemId;
    private final String cfProblemId;
    private final Verdict verdict;
    private final Integer executionTimeMs;
    private final LocalDateTime submittedAt;

    public Submission(UUID id, UUID studentId, UUID problemId, String cfProblemId, Verdict verdict, Integer executionTimeMs, LocalDateTime submittedAt) {
        this.id = id;
        this.studentId = studentId;
        this.problemId = problemId;
        this.cfProblemId = cfProblemId;
        this.verdict = verdict;
        this.executionTimeMs = executionTimeMs;
        this.submittedAt = submittedAt;
    }

    public boolean isAccepted(){
        return this.verdict == Verdict.OK;
    }

    public void updateProblemId(UUID problemId) {
        this.problemId = problemId;
    }

    public UUID getId() {
        return id;
    }

    public UUID getStudentId() {
        return studentId;
    }

    public UUID getProblemId() {
        return problemId;
    }

    public String getCfProblemId() {
        return cfProblemId;
    }

    public Verdict getVerdict() {
        return verdict;
    }

    public Integer getExecutionTimeMs() {
        return executionTimeMs;
    }

    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }
}
