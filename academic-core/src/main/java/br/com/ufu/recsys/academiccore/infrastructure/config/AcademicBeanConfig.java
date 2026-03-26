package br.com.ufu.recsys.academiccore.infrastructure.config;

import br.com.ufu.recsys.academiccore.application.usecases.SyncStudentProfileUseCase;
import br.com.ufu.recsys.academiccore.domain.ports.in.SyncStudentProfilePort;
import br.com.ufu.recsys.academiccore.domain.ports.out.CodeforcesApiPort;
import br.com.ufu.recsys.academiccore.domain.ports.out.StudentProfileRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AcademicBeanConfig {

    @Bean
    public SyncStudentProfilePort syncStudentProfilePort(
            StudentProfileRepositoryPort repositoryPort,
            CodeforcesApiPort codeforcesApiPort) {
        return new SyncStudentProfileUseCase(repositoryPort, codeforcesApiPort);
    }
}
