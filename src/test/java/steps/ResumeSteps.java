import io.cucumber.java.de.*;
import org.springframework.web.reactive.function.client.WebClient;

public class ResumeSteps {
    private final WebClient client = WebClient.create("http://localhost:8080");

    @Dann("warte ich bis der Test fortgesetzt werden kann")
    public void warte_auf_resume() throws InterruptedException {
        while (true) {
            TestContext context = client.get()
                .uri("/resume/resume-1")
                .retrieve()
                .bodyToMono(TestContext.class)
                .block();

            if (context != null) {
                break; // Test kann fortgesetzt werden
            }
            Thread.sleep(5000); // 5 Sekunden warten und erneut versuchen
        }
    }
}