package br.com.ufu.recsys.competitivecore.adapters.in.web;

import br.com.ufu.recsys.competitivecore.application.ports.in.SyncUserSubmissionsPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/submissions")
public class SubmissionController {

    private final SyncUserSubmissionsPort syncUserSubmissionsPort;

    public SubmissionController(SyncUserSubmissionsPort syncUserSubmissionsPort) {
        this.syncUserSubmissionsPort = syncUserSubmissionsPort;
    }

    @PostMapping("/sync-user/{studentId}/{handle}")
    public ResponseEntity<String> syncSubmissions(
            @PathVariable UUID studentId,
            @PathVariable String handle) {

        syncUserSubmissionsPort.syncSubmissions(studentId, handle);

        return ResponseEntity.ok("Submissões do aluno " + handle + " sincronizadas e cruzadas com sucesso!");
    }
}