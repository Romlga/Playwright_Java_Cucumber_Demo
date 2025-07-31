package entity;

import java.time.LocalDateTime;
import java.util.UUID;

public class TestContext {
    private UUID id;
    private String testName;
    private String stepName;
    private String trigger;
    private String status;
    private LocalDateTime resumeTimestamp;

    public TestContext() {}

    public TestContext(UUID id, String testName, String stepName, String trigger, String status, LocalDateTime resumeTimestamp) {
        this.id = id;
        this.testName = testName;
        this.stepName = stepName;
        this.trigger = trigger;
        this.status = status;
        this.resumeTimestamp = resumeTimestamp;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getTestName() { return testName; }
    public void setTestName(String testName) { this.testName = testName; }

    public String getStepName() { return stepName; }
    public void setStepName(String stepName) { this.stepName = stepName; }

    public String getTrigger() { return trigger; }
    public void setTrigger(String trigger) { this.trigger = trigger; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getResumeTimestamp() { return resumeTimestamp; }
    public void setResumeTimestamp(LocalDateTime resumeTimestamp) { this.resumeTimestamp = resumeTimestamp; }
}
