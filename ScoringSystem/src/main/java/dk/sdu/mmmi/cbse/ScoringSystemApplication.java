package dk.sdu.mmmi.cbse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ScoringSystemApplication {
    private Long totalScore = 0L;

    public static void main(String[] args) {
        SpringApplication.run(ScoringSystemApplication.class, args);
    }

    @GetMapping("/getScore")
    public Long getScore() {
        return totalScore ;
    }

    @PostMapping("/addScore")
    public Long addScore(@RequestParam("score") Long score) {
        if (score == null) return totalScore;

        totalScore += score;
        return totalScore ;
    }
}