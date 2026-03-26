package br.com.ufu.recsys.academiccore.adapters.out.persistence;

import br.com.ufu.recsys.academiccore.domain.model.StudentProfile;
import br.com.ufu.recsys.academiccore.domain.ports.out.StudentProfileRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class StudentProfileRepositoryAdapter implements StudentProfileRepositoryPort {
    private final StudentProfileRepositoryJpa studentProfileRepositoryJpa;
    private final StudentProfileMapper studentProfileMapper;

    public StudentProfileRepositoryAdapter(StudentProfileRepositoryJpa studentProfileRepositoryJpa, StudentProfileMapper studentProfileMapper) {
        this.studentProfileRepositoryJpa = studentProfileRepositoryJpa;
        this.studentProfileMapper = studentProfileMapper;
    }

    @Override
    public StudentProfile save(StudentProfile studentProfile) {
        var entity = studentProfileMapper.toJpaEntity(studentProfile);
        var savedEntity = studentProfileRepositoryJpa.save(entity);
        return studentProfileMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<StudentProfile> findByUserId(UUID id) {
        return studentProfileRepositoryJpa.findByUserId(id).map(studentProfileMapper::toDomain);
    }

    @Override
    public Optional<StudentProfile> findByhandle(String handle) {
        return studentProfileRepositoryJpa.findByHandle(handle).map(studentProfileMapper::toDomain);
    }
}
