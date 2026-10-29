package concurrency;

import java.util.concurrent.*;

public class TransactionProcessor {
private final ExecutorService executor =
Executors.newFixedThreadPool(4);

public void submit(Runnable task) {
    executor.execute(task);
}

public void stop() {
    executor.shutdown();

    try {
        executor.awaitTermination(1, TimeUnit.MINUTES);
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
    }
}

}

