package jsanca.download.internal.execution;

import java.util.Objects;

/**
 * Internal abstraction responsible for executing download-related work.
 *
 * <p>This interface hides the concurrency strategy used by the downloader. Implementations
 * may use virtual threads, platform threads, semaphores, queues, or other execution
 * policies without exposing those details to higher-level components.
 * @author jsanca & elo
 */
public interface DownloadExecutor {

    /**
     * Executes the provided task according to the configured execution strategy.
     *
     * @param task the task to execute
     * @throws NullPointerException if {@code task} is {@code null}
     */
    void execute(Runnable task);

    /**
     * Validates that the provided task is not {@code null}.
     *
     * <p>This helper centralizes a small but common validation used by implementations.
     * Keeping it here avoids repeating the same null-check logic across executor classes.
     *
     * @param task the task to validate
     * @throws NullPointerException if {@code task} is {@code null}
     */
    static void validateTask(Runnable task) {
        Objects.requireNonNull(task, "task must not be null");
    }
}
