package br.com.ufu.recsys.academiccore.adapters.in.web;

import br.com.ufu.recsys.academiccore.adapters.in.web.dto.ProfileResponseDto;
import br.com.ufu.recsys.academiccore.adapters.in.web.dto.SyncProfileRequestDto;
import br.com.ufu.recsys.academiccore.domain.model.StudentProfile;
import br.com.ufu.recsys.academiccore.domain.ports.in.SyncStudentProfilePort;
import br.com.ufu.recsys.academiccore.domain.ports.out.StudentProfileRepositoryPort;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController {
    private final SyncStudentProfilePort syncStudentProfilePort;

    public ProfileController(SyncStudentProfilePort syncStudentProfilePort) {
        this.syncStudentProfilePort = syncStudentProfilePort;
    }

    @PostMapping("/sync")
    public ResponseEntity<ProfileResponseDto> sync(@RequestBody SyncProfileRequestDto syncProfileRequestDto) {
        StudentProfile profile = syncStudentProfilePort.syncCodeforcesProfile(syncProfileRequestDto.userId(), syncProfileRequestDto.handle());

        ProfileResponseDto response = new ProfileResponseDto(
                profile.getId(),
                profile.getUserId(),
                profile.getPlatform().name(),
                profile.getHandle(),
                profile.getCurrentRating(),
                profile.getCurrentRank(),
                profile.getLastSyncedAt()
        );

        return ResponseEntity.ok(response);
    }
}
