
package steps;

import io.cucumber.java.de.*;
import org.springframework.web.reactive.function.client.WebClient;
import java.time.LocalDateTime;
import entity.TestContext;

public class PauseSteps {
    private final WebClient client = WebClient.create("http://localhost:8080");

    @Wenn("ich den Test pausieren möchte bis {string}")
    public void ich_den_test_pausieren_moechte_bis(String resumeTime) {

        TestContext context = new TestContext();
        context.setTestName("MeinTest1");
        context.setStepName("Login");
        context.setTrigger("resume-1");
        context.setStatus("PAUSED");
        context.setResumeTimestamp(LocalDateTime.parse(resumeTime));

        client.post()
            .uri("/pause")
            .bodyValue(context)
            .retrieve()
            .bodyToMono(TestContext.class)
            .block();
    }
}