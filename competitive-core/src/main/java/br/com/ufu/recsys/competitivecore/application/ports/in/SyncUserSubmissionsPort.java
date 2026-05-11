package br.com.ufu.recsys.competitivecore.application.ports.in;

import java.util.UUID;

public interface SyncUserSubmissionsPort {
    void syncSubmissions(UUID studentId, String handle);
}
