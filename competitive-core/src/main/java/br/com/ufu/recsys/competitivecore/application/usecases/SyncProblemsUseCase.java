package br.com.ufu.recsys.competitivecore.application.usecases;

import br.com.ufu.recsys.competitivecore.application.ports.in.SyncProblemsPort;
import br.com.ufu.recsys.competitivecore.application.ports.out.CodeforcesApiPort;
import br.com.ufu.recsys.competitivecore.application.ports.out.ProblemRepositoryPort;

public class SyncProblemsUseCase implements SyncProblemsPort {

    private final CodeforcesApiPort codeforcesApiPort;
    private final ProblemRepositoryPort problemRepositoryPort;

    public SyncProblemsUseCase(CodeforcesApiPort codeforcesApiPort, ProblemRepositoryPort problemRepositoryPort) {
        this.codeforcesApiPort = codeforcesApiPort;
        this.problemRepositoryPort = problemRepositoryPort;
    }

    @Override
    public void syncAllProblems() {
        var problems = codeforcesApiPort.fetchAllProblems();

        if (!problems.isEmpty()) {
            problemRepositoryPort.saveAll(problems);
            System.out.println(problems.size() + " problemas sincronizados com sucesso!");
        }
    }
}