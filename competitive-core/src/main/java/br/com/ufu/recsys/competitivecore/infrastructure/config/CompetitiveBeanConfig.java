package br.com.ufu.recsys.competitivecore.infrastructure.config;

import br.com.ufu.recsys.competitivecore.application.ports.in.SyncProblemsPort;
import br.com.ufu.recsys.competitivecore.application.ports.in.SyncUserSubmissionsPort;
import br.com.ufu.recsys.competitivecore.application.ports.out.CodeforcesApiPort;
import br.com.ufu.recsys.competitivecore.application.ports.out.ProblemRepositoryPort;
import br.com.ufu.recsys.competitivecore.application.ports.out.SubmissionRepositoryPort;
import br.com.ufu.recsys.competitivecore.application.usecases.SyncProblemsUseCase;
import br.com.ufu.recsys.competitivecore.application.usecases.SyncUserSubmissionsUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CompetitiveBeanConfig {

    @Bean
    public SyncProblemsPort syncProblemsPort(
            CodeforcesApiPort codeforcesApiPort,
            ProblemRepositoryPort problemRepositoryPort) {

        return new SyncProblemsUseCase(codeforcesApiPort, problemRepositoryPort);
    }

    @Bean
    public SyncUserSubmissionsPort syncUserSubmissionsPort(
            CodeforcesApiPort codeforcesApiPort,
            SubmissionRepositoryPort submissionRepositoryPort,
            ProblemRepositoryPort problemRepositoryPort) {
        return new SyncUserSubmissionsUseCase(codeforcesApiPort, submissionRepositoryPort, problemRepositoryPort);
    }
}
