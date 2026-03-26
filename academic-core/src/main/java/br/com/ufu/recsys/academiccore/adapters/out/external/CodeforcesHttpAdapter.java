package br.com.ufu.recsys.academiccore.adapters.out.external;

import br.com.ufu.recsys.academiccore.adapters.out.external.dto.CodeforcesResponseDto;
import br.com.ufu.recsys.academiccore.domain.ports.out.CodeforcesApiPort;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class CodeforcesHttpAdapter implements CodeforcesApiPort {

    private final RestClient restClient;

    public CodeforcesHttpAdapter() {
        this.restClient = RestClient.builder()
                .baseUrl("https://codeforces.com/api")
                .build();
    }

    @Override
    public CodeforcesStats fetchUserStats(String handle) {
        try{
            CodeforcesResponseDto response = restClient.get()
                    .uri("/user.info?handles={handle}", handle)
                    .retrieve()
                    .body(CodeforcesResponseDto.class);
            if(response != null && "OK".equals(response.status()) && !response.result().isEmpty()) {
                var user =  response.result().get(0);
                return new CodeforcesStats(user.rating(), user.rank());
            }
        }
        catch(Exception e){
            System.err.println("Erro ao buscar dados do Codeforces: " + e.getMessage());
        }

        return null;
    }
}
