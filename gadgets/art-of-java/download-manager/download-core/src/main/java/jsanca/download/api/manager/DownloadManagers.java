package jsanca.download.api.manager;

import jsanca.download.api.event.*;
import jsanca.download.api.model.DownloadConfig;
import jsanca.download.api.model.DownloadInfo;
import jsanca.download.api.model.DownloadRequest;
import jsanca.download.internal.execution.DownloadExecutor;
import jsanca.download.internal.execution.DownloadExecutors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

/**
 * Factory for creating {@link DownloadManager} instances.
 *
 * @author jsanca + elo
 */
public final class DownloadManagers {

    private DownloadManagers() {
        throw new UnsupportedOperationException("Utility class should not be instantiated");
    }

    /**
     * Creates a default {@link DownloadManager} with sensible defaults.
     *
     * @return a default download manager
     */
    public static DownloadManager createDefault() {
        return createDefault(DownloadConfig.defaultConfig());
    }

    /**
     * Creates a default {@link DownloadManager} using the provided configuration.
     *
     * @param config the downloader configuration
     * @return a configured download manager
     */
    public static DownloadManager createDefault(DownloadConfig config) {
        Objects.requireNonNull(config, "config must not be null");
        return new DefaultDownloadManager(config, DownloadExecutors.createVirtualThreadExecutor(config));
    }

    /**
     * Default implementation kept hidden inside the factory.
     */
    static final class DefaultDownloadManager implements DownloadManager {

        private static final Logger log = LoggerFactory.getLogger(DefaultDownloadManager.class);

        private final DownloadConfig config;
        private final DownloadExecutor executor;
        private final List<Consumer<DownloadEvent>> listeners = new CopyOnWriteArrayList<>();

        DefaultDownloadManager(final DownloadConfig config, final DownloadExecutor executor) {
            this.config = Objects.requireNonNull(config, "config must not be null");
            this.executor = Objects.requireNonNull(executor, "executor must not be null");
        }

        @Override
        public DownloadInfo submit(final DownloadRequest request) {

            Objects.requireNonNull(request, "request must not be null");

            final DownloadInfo info = new DownloadInfo(
                    UUID.randomUUID().toString(),
                    request.sourceUri(),
                    request.targetPath()
            );

            log.debug("Submitting download: {}", info);

            this.executor.execute(() -> runDownload(info));
            return info;
        }

        @Override
        public void addListener(final Consumer<DownloadEvent> listener) {

            Objects.requireNonNull(listener, "listener must not be null");
            this.listeners.add(listener);
        }

        @Override
        public void removeListener(final Consumer<DownloadEvent> listener) {

            this.listeners.remove(listener);
        }

        private void runDownload(final DownloadInfo info) {

            Objects.requireNonNull(info, "info must not be null");
            log.debug("Starting download: {}", info);
            final Instant occurredAt = Instant.now();
            this.emit(new DownloadStartedEvent(info, occurredAt));
            log.debug("Download started: {}", info.downloadId());

            try {
                long totalBytes = 100L;
                log.debug("Simulating download for {} bytes", totalBytes);
                long downloaded = 0L;

                final Instant startTime = occurredAt;

                while (downloaded < totalBytes) {
                    // simulate work
                    Thread.sleep(100);

                    downloaded += 20;
                    if (downloaded > totalBytes) {
                        downloaded = totalBytes;
                    }

                    log.debug("Progress {}: {}/{} bytes", info.downloadId(), downloaded, totalBytes);

                    this.emit(new DownloadProgressEvent(
                            info,
                            downloaded,
                            totalBytes,
                            Instant.now()
                    ));
                }

                final Duration duration = Duration.between(startTime, Instant.now());
                log.debug("Download completed: {} in {} ms", info.downloadId(), duration.toMillis());

                this.emit(new DownloadCompletedEvent(
                        info,
                        info.targetPath(),
                        totalBytes,
                        null,
                        duration,
                        Instant.now()
                ));

            } catch (Exception e) {
                Duration duration = Duration.between(occurredAt, Instant.now());
                log.error("Download failed: {}", info.downloadId(), e);

                this.emit(new DownloadFailedEvent(
                        info,
                        e.getMessage(),
                        e,
                        duration,
                        Instant.now()
                ));
            }
        }

        private void emit(final DownloadEvent event) {

            Objects.requireNonNull(event, "event must not be null");
            for (final Consumer<DownloadEvent> listener : listeners) {

                listener.accept(event);
            }
        }
    }
}
