package model;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public record Task(UUID id, String summary, int priority, LocalDate deadlineDate, Status status) {
    public Task {
        Objects.requireNonNull(summary, "summary can not be null");
        Objects.requireNonNull(status, "status can not be null");
        if (priority < 1) {
            throw new IllegalArgumentException("priority can not be lesser than 1");
        }
    }

    public Task withId(UUID id) {
        return new Task(id, summary, priority, deadlineDate, status);
    }

    public Task withStatus(Status status) {
        return new Task(id, summary, priority, deadlineDate, status);
    }
}
