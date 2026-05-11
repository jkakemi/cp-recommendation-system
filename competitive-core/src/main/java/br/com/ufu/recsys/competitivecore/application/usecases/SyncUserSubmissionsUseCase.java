package br.com.ufu.recsys.competitivecore.application.usecases;

import br.com.ufu.recsys.competitivecore.application.ports.in.SyncUserSubmissionsPort;
import br.com.ufu.recsys.competitivecore.application.ports.out.CodeforcesApiPort;
import br.com.ufu.recsys.competitivecore.application.ports.out.ProblemRepositoryPort;
import br.com.ufu.recsys.competitivecore.application.ports.out.SubmissionRepositoryPort;

import java.util.UUID;

public class SyncUserSubmissionsUseCase implements SyncUserSubmissionsPort {

    private final CodeforcesApiPort codeforcesApiPort;
    private final SubmissionRepositoryPort submissionRepositoryPort;
    private final ProblemRepositoryPort problemRepositoryPort;

    public SyncUserSubmissionsUseCase(CodeforcesApiPort codeforcesApiPort,
                                      SubmissionRepositoryPort submissionRepositoryPort,
                                      ProblemRepositoryPort problemRepositoryPort) {
        this.codeforcesApiPort = codeforcesApiPort;
        this.submissionRepositoryPort = submissionRepositoryPort;
        this.problemRepositoryPort = problemRepositoryPort;
    }

    @Override
    public void syncSubmissions(UUID studentId, String handle) {
        var apiSubmissions = codeforcesApiPort.fetchUserSubmissions(studentId, handle);

        for (var submission : apiSubmissions) {
            problemRepositoryPort.findByCfId(submission.getCfProblemId())
                    .ifPresent(problem -> submission.updateProblemId(problem.getId()));
        }

        submissionRepositoryPort.saveAll(apiSubmissions);
    }
}
