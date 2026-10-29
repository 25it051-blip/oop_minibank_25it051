package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.time.LocalDateTime;

public final class TransactionLog {
private static final Path LOG_PATH = Paths.get("data", "transactions.log");

private TransactionLog() {}

public static synchronized void log(String message) {
    try {
        Files.createDirectories(LOG_PATH.getParent());

        String line = LocalDateTime.now() + " - " + message
                + System.lineSeparator();

        Files.writeString(LOG_PATH, line, StandardCharsets.UTF_8,
                StandardOpenOption.CREATE, StandardOpenOption.APPEND);

    } catch (IOException e) {
        System.err.println("Log Error: " + e.getMessage());
    }
}
}
