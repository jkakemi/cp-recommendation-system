package br.com.ufu.recsys.academiccore.domain.ports.in;

import br.com.ufu.recsys.academiccore.domain.model.StudentProfile;

import java.util.UUID;

public interface SyncStudentProfilePort {
    StudentProfile syncCodeforcesProfile(UUID userId, String handle);
}
