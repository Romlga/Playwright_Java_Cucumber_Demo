
package steps;

import io.cucumber.java.en.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
// Import TestContext if it exists in your project
import entity.TestContext;


public class PauseSteps {
    public static String scenarioName = "";
    public static String masterRunId="run-" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));

    // Beispiel-JSONs
    private static final String JSON_EMPTY = "{}";
    private static final String JSON_ONE_PARAM = "{\"foo\":\"bar\"}";
    private static final String JSON_TWO_PARAMS = "{\"foo\":\"bar\",\"x\":123}";
    private static final String JSON_THREE_PARAMS = "{\"foo\":\"bar\",\"x\":123,\"flag\":true}";

    @When("ich den Test pausieren möchte bis {string}")
    public void ich_den_test_pausieren_moechte_bis(String resumeTime) throws IOException, InterruptedException {
        System.out.println("Step: ich den Test pausieren möchte bis " + resumeTime);
        System.out.println("Szenario: " + scenarioName);

        masterRunId = System.getProperty("masterRunId", "undefined-runId");
        System.out.println("💡 MasterRunId set to: " + masterRunId);


        // Beispiel: JSON mit 3 Parametern anhängen
        String variablesJson = JSON_THREE_PARAMS;
        System.out.println("Beispiel-JSON: " + variablesJson);

        TestContext context = TestContext.builder().masterRunId(masterRunId)
            .testName(scenarioName)
            .trigger("resume")
            .resumeTimestamp(LocalDateTime.parse(resumeTime))
            .variablesJson(variablesJson) // JSON wird mitgegeben
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