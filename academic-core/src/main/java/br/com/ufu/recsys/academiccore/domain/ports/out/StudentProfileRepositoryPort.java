package br.com.ufu.recsys.academiccore.domain.ports.out;

import br.com.ufu.recsys.academiccore.domain.model.StudentProfile;

import java.util.Optional;
import java.util.UUID;

public interface StudentProfileRepositoryPort {
    StudentProfile save(StudentProfile studentProfile);
    Optional<StudentProfile> findByUserId(UUID id);
    Optional<StudentProfile> findByhandle(String handle);
}
