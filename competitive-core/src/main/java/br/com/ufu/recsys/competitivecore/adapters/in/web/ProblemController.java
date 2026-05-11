package br.com.ufu.recsys.competitivecore.adapters.in.web;

import br.com.ufu.recsys.competitivecore.application.ports.in.SyncProblemsPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/problems")
public class ProblemController {

    private final SyncProblemsPort syncProblemsPort;

    public ProblemController(SyncProblemsPort syncProblemsPort) {
        this.syncProblemsPort = syncProblemsPort;
    }

    @PostMapping("/sync")
    public ResponseEntity<String> syncProblems() {
        syncProblemsPort.syncAllProblems();

        return ResponseEntity.ok("Catálogo de problemas do Codeforces sincronizado e salvo no banco de dados!");
    }
}