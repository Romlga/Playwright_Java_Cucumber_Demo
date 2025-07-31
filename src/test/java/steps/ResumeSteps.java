
package steps;

import io.cucumber.java.de.*;
import org.springframework.web.reactive.function.client.WebClient;
import entity.TestContext;



public class ResumeSteps {
    public static TestContext context;

    @Dann("ich setze den Test nach Pause fort")
    public void ich_setze_den_test_nach_pause_fort() {
    context = WebClient.create("http://localhost:8080")
        .get()
        .uri("/resume/resume-1")
        .retrieve()
        .bodyToMono(TestContext.class)
        .block();

    }
}