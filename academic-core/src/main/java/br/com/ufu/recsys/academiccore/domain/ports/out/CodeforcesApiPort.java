package br.com.ufu.recsys.academiccore.domain.ports.out;

public interface CodeforcesApiPort {
    record CodeforcesStats(Integer rating, String rank) {}

    CodeforcesStats fetchUserStats(String handle);

}
