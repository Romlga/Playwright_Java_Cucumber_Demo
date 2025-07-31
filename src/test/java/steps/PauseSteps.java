import io.cucumber.java.de.*;
import org.springframework.web.reactive.function.client.WebClient;
import java.time.LocalDateTime;

public class PauseSteps {
    private final WebClient client = WebClient.create("http://localhost:8080");

    @Wenn("ich den Test pausieren möchte bis {string}")
    public void ich_den_test_pausieren_moechte_bis(String resumeTime) {
        TestContext context = TestContext.builder()
            .testName("MeinTest1")
            .stepName("Login")
            .trigger("resume-1")
            .status("PAUSED")
            .resumeTimestamp(LocalDateTime.parse(resumeTime))
            .build();

        client.post()
            .uri("/pause")
            .bodyValue(context)
            .retrieve()
            .bodyToMono(TestContext.class)
            .block();
    }
}