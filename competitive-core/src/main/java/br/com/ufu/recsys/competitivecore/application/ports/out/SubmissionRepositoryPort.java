package br.com.ufu.recsys.competitivecore.application.ports.out;

import br.com.ufu.recsys.competitivecore.domain.model.Submission;

import java.util.List;

public interface SubmissionRepositoryPort {
    void saveAll(List<Submission> submissions);
}
