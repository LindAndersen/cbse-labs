package dk.sdu.mmmi.cbse;

import dk.sdu.mmmi.cbse.common.services.IScoreSystemService;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public class ScoringClient implements IScoreSystemService {
    private final RestTemplate restTemplate = new RestTemplate();
    private static final String BASE_URL = "http://localhost:8080";

    public ScoringClient(){}

    @Override
    public long addScore(long score) {
        try {
            String totalScore = restTemplate.postForObject(BASE_URL + "/addScore?score=" + score, null, String.class);
            return totalScore != null ? Long.parseLong(totalScore) : 0L;
        } catch (RestClientException e) {
            System.err.println(e.getMessage());
            return 0L;
        }
    }

    @Override
    public long getScore() {
        try {
            String score = restTemplate.getForObject(BASE_URL + "/getScore", String.class);
            return score != null ? Long.parseLong(score) : 0L;
        } catch (RestClientException e) {
            System.err.println(e.getMessage());
            return 0L; // or return -1L to indicate failure
        }
    }
}
