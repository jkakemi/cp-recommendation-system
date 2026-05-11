package br.com.ufu.recsys.competitivecore.application.ports.out;

import br.com.ufu.recsys.competitivecore.domain.model.Problem;
import br.com.ufu.recsys.competitivecore.domain.model.Submission;

import java.util.List;
import java.util.UUID;

public interface CodeforcesApiPort {
    List<Problem> fetchAllProblems();
    List<Submission> fetchUserSubmissions(UUID studentId, String handle);

}
