
package steps;

import io.cucumber.java.en.*;
import org.springframework.web.reactive.function.client.WebClient;
import java.time.LocalDateTime;
// Import TestContext if it exists in your project
import entity.TestContext;


public class PauseSteps {
    public static String scenarioName = "";

    @When("ich den Test pausieren möchte bis {string}")
    public void ich_den_test_pausieren_moechte_bis(String resumeTime) {
        System.out.println("Step: ich den Test pausieren möchte bis " + resumeTime);
        System.out.println("Szenario: " + scenarioName);
        TestContext context = TestContext.builder()
            .testName(scenarioName)
            .trigger("resume")
            .resumeTimestamp(LocalDateTime.parse(resumeTime))
            .build();

        WebClient.create("http://localhost:8080")
            .post()
            .uri("/pause")
            .bodyValue(context)
            .retrieve()
            .bodyToMono(TestContext.class)
            .block();
        System.out.println("EXIT");
    }
}