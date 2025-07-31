package entity;


import lombok.Data;
import lombok.Builder;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class TestContext {
    private UUID id;
    private String testName;
    private String stepName;
    private String trigger;
    private String status;
    private LocalDateTime resumeTimestamp;
}