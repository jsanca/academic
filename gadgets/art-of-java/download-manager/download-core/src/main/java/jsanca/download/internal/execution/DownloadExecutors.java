package jsanca.download.internal.execution;

import jsanca.download.api.model.DownloadConfig;

import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Factory for creating {@link DownloadExecutor} instances.
 * @author jsanca & elo
 */
public final class DownloadExecutors {

    private DownloadExecutors() {
        throw new UnsupportedOperationException("Utility class should not be instantiated");
    }

    /**
     * Creates a default executor using virtual threads and a semaphore for concurrency control.
     *
     * @param config the download configuration
     * @return a configured {@link DownloadExecutor}
     */
    public static DownloadExecutor createVirtualThreadExecutor(final DownloadConfig config) {

        Objects.requireNonNull(config, "config must not be null");
        return new VirtualThreadDownloadExecutor(config.maxConcurrentDownloads());
    }

    /**
     * Default implementation using virtual threads + semaphore.
     */
    static final class VirtualThreadDownloadExecutor implements DownloadExecutor {

        private static final Logger log = LoggerFactory.getLogger(VirtualThreadDownloadExecutor.class);

        private final ExecutorService executor;
        private final Semaphore semaphore;

        VirtualThreadDownloadExecutor(final int maxConcurrentDownloads) {
            if (maxConcurrentDownloads <= 0) {
                throw new IllegalArgumentException("maxConcurrentDownloads must be > 0");
            }
            this.executor = Executors.newVirtualThreadPerTaskExecutor();
            this.semaphore = new Semaphore(maxConcurrentDownloads);
        }

        @Override
        public void execute(final Runnable task) {

            DownloadExecutor.validateTask(task);

            log.debug("Submitting task to executor");
            this.executor.submit(() -> {
                boolean acquired = false;
                try {
                    log.debug("Attempting to acquire permit...");
                    this.semaphore.acquire();
                    acquired = true;
                    log.debug("Permit acquired");
                    log.debug("Executing task");
                    task.run();
                } catch (InterruptedException e) {
                    log.warn("Task interrupted while waiting for permit", e);
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("Task execution interrupted", e);
                } finally {
                    if (acquired) {
                        log.debug("Releasing permit");
                        semaphore.release();
                    }
                }
            });
        }
    }
}
