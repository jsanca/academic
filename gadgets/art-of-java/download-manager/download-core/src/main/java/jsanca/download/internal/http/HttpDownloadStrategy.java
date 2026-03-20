
package jsanca.download.internal.http;

import jsanca.download.api.event.DownloadCompletedEvent;
import jsanca.download.api.event.DownloadEvent;
import jsanca.download.api.event.DownloadFailedEvent;
import jsanca.download.api.event.DownloadProgressEvent;
import jsanca.download.api.model.DownloadInfo;
import jsanca.download.internal.strategy.DownloadStrategy;
import jsanca.download.internal.util.PathPreparer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.time.Duration;
import java.time.Instant;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * HTTP-based implementation of {@link DownloadStrategy} using the JDK {@link HttpClient}.
 *
 * <p>This strategy performs a blocking stream download and emits progress, completion,
 * and failure events through the provided emitter. It is designed to be executed by the
 * downloader execution layer, which may run it on virtual threads.
 * @author jsanca & elo
 */
public final class HttpDownloadStrategy implements DownloadStrategy {

    private static final Logger log = LoggerFactory.getLogger(HttpDownloadStrategy.class);
    private static final int DEFAULT_BUFFER_SIZE = 8 * 1024;

    private final HttpClient httpClient;
    private final int bufferSize;

    /**
     * Creates a strategy using a default JDK {@link HttpClient}.
     */
    public HttpDownloadStrategy() {
        this(HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build(), DEFAULT_BUFFER_SIZE);
    }

    /**
     * Creates a strategy with a custom HTTP client and buffer size.
     *
     * @param httpClient the HTTP client to use
     * @param bufferSize the stream buffer size in bytes
     */
    public HttpDownloadStrategy(final HttpClient httpClient,
                                final int bufferSize) {
        
        this.httpClient = Objects.requireNonNull(httpClient, "httpClient must not be null");
        if (bufferSize <= 0) {
            throw new IllegalArgumentException("bufferSize must be > 0");
        }
        this.bufferSize = bufferSize;
    }

    @Override
    public void download(DownloadInfo info, Consumer<DownloadEvent> emitter) {
        DownloadStrategy.validate(info, emitter);

        Instant startedAt = Instant.now();
        log.debug("Starting HTTP download for {} from {}", info.downloadId(), info.sourceUri());

        try {
            PathPreparer.prepareParentDirectory(info.targetPath());

            HttpRequest request = HttpRequest.newBuilder(info.sourceUri())
                    .GET()
                    .build();

            HttpResponse<InputStream> response = httpClient.send(request, HttpResponse.BodyHandlers.ofInputStream());
            int statusCode = response.statusCode();

            if (statusCode < 200 || statusCode >= 300) {
                throw new IOException("Unexpected HTTP status code: " + statusCode);
            }

            long totalBytes = response.headers()
                    .firstValueAsLong("Content-Length")
                    .orElse(0L);

            log.debug("HTTP response OK for {}. contentLength={}", info.downloadId(), totalBytes);

            try (InputStream inputStream = response.body();
                 OutputStream outputStream = Files.newOutputStream(info.targetPath())) {

                byte[] buffer = new byte[bufferSize];
                long downloadedBytes = 0L;
                int bytesRead;

                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                    downloadedBytes += bytesRead;

                    log.debug("HTTP progress {}: {}/{} bytes",
                            info.downloadId(), downloadedBytes, totalBytes);

                    emitter.accept(new DownloadProgressEvent(
                            info,
                            downloadedBytes,
                            totalBytes,
                            Instant.now()
                    ));
                }

                outputStream.flush();

                Duration duration = Duration.between(startedAt, Instant.now());
                log.debug("HTTP download completed for {} in {} ms",
                        info.downloadId(), duration.toMillis());

                emitter.accept(new DownloadCompletedEvent(
                        info,
                        info.targetPath(),
                        downloadedBytes,
                        null,
                        duration,
                        Instant.now()
                ));
            }
        } catch (Exception e) {
            Duration duration = Duration.between(startedAt, Instant.now());
            log.error("HTTP download failed for {}", info.downloadId(), e);

            emitter.accept(new DownloadFailedEvent(
                    info,
                    e.getMessage(),
                    e,
                    duration,
                    Instant.now()
            ));
        }
    }
}
