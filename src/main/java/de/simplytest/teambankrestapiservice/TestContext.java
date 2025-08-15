package de.simplytest.teambankrestapiservice;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.ALWAYS)
public class TestContext {
    @Id
    @GeneratedValue
    private UUID id;
    private String testName;
    private String trigger;
    private String status;
    private LocalDateTime resumeTimestamp;
    @Column(columnDefinition = "TEXT")
    private String variablesJson;

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getTestName() {
        return testName;
    }
    public void setTestName(String testName) {
        this.testName = testName;
    }
    public String getTrigger() {
        return trigger;
    }
    public void setTrigger(String trigger) {
        this.trigger = trigger;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public LocalDateTime getResumeTimestamp() {
        return resumeTimestamp;
    }
    public void setResumeTimestamp(LocalDateTime resumeTimestamp) {
        this.resumeTimestamp = resumeTimestamp;
    }
}
