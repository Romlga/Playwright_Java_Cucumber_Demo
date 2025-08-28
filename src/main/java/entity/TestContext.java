package entity;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TestContext {
    private UUID id;
    private String testName;
    private String stepName;
    private String trigger;
    private String status;
    private LocalDateTime resumeTimestamp;
    private String variablesJson;
    private String masterRunId;
    // Getter und Setter für variablesJson (falls Lombok nicht reicht)
    public String getVariablesJson() {
        return variablesJson;
    }
    public void setVariablesJson(String variablesJson) {
        this.variablesJson = variablesJson;
    }
}