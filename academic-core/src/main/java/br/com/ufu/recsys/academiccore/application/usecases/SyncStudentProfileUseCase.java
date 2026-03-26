package br.com.ufu.recsys.academiccore.application.usecases;


import br.com.ufu.recsys.academiccore.domain.model.StudentProfile;
import br.com.ufu.recsys.academiccore.domain.ports.in.SyncStudentProfilePort;
import br.com.ufu.recsys.academiccore.domain.ports.out.CodeforcesApiPort;
import br.com.ufu.recsys.academiccore.domain.ports.out.StudentProfileRepositoryPort;

import java.util.UUID;

public class SyncStudentProfileUseCase implements SyncStudentProfilePort {

    private final StudentProfileRepositoryPort studentProfileRepositoryPort;
    private final CodeforcesApiPort codeforcesApiPort;

    public SyncStudentProfileUseCase(StudentProfileRepositoryPort studentProfileRepositoryPort, CodeforcesApiPort codeforcesApiPort) {
        this.studentProfileRepositoryPort = studentProfileRepositoryPort;
        this.codeforcesApiPort = codeforcesApiPort;
    }

    @Override
    public StudentProfile syncCodeforcesProfile(UUID userId, String handle) {
        StudentProfile profile = studentProfileRepositoryPort.findByUserId(userId)
                .orElse(new StudentProfile(UUID.randomUUID(), userId, handle));

        var stats = codeforcesApiPort.fetchUserStats(handle);

        if(stats != null){
            profile.updateStats(stats.rating(), stats.rank());
        }

        return studentProfileRepositoryPort.save(profile);
    }
}
