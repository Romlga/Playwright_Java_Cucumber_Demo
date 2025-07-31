package entity;


import java.time.LocalDateTime;
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
}