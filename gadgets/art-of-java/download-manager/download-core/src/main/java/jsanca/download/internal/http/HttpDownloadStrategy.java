package jsanca.download.internal.http;

import jsanca.download.api.event.*;
import jsanca.download.api.model.DownloadInfo;
import jsanca.download.internal.exception.DownloadCancelledException;
import jsanca.download.internal.exception.HttpDownloadStatusException;
import jsanca.download.internal.model.DownloadTask;
import jsanca.download.internal.strategy.DownloadStrategy;
import jsanca.download.internal.util.MapperExceptionUtil;
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
    public void download(DownloadTask task, final Consumer<DownloadEvent> emitter) {

        DownloadStrategy.validate(task, emitter);

        final DownloadInfo info = task.info();

        final Instant startedAt = Instant.now();
        log.debug("Starting HTTP download for {} from {}", info.downloadId(), info.sourceUri());

        try {

            PathPreparer.prepareParentDirectory(info.targetPath());

            final HttpResponse<InputStream> response = doGet(info);
            final int statusCode = response.statusCode();

            if (isInvalidResponseStatus(statusCode)) {

                throw new HttpDownloadStatusException(info, statusCode);
            }

            final long totalBytes = response.headers()
                    .firstValueAsLong("Content-Length")
                    .orElse(0L);

            log.debug("HTTP response OK for {}. contentLength={}", info.downloadId(), totalBytes);

            try (InputStream inputStream = response.body();
                 OutputStream outputStream = Files.newOutputStream(info.targetPath())) {

                final long downloadedBytes = doDownload(task, inputStream, outputStream, totalBytes, emitter);
                outputStream.flush();
                onComplete(info, emitter, startedAt, downloadedBytes);
            }
        } catch (DownloadCancelledException e) {

            onCancelled(info, emitter, e);
        } catch (Exception e) {

            onError(info, emitter, e, startedAt);
        }
    }

    private static void onComplete(final DownloadInfo info,
                                   final Consumer<DownloadEvent> emitter,
                                   final Instant startedAt,
                                   final long downloadedBytes) {

        final Duration duration = Duration.between(startedAt, Instant.now());
        log.debug("HTTP download completed for {} in {} ms", info.downloadId(), duration.toMillis());

        emitter.accept(new DownloadCompletedEvent(
                info,
                info.targetPath(),
                downloadedBytes,
                null,
                duration,
                Instant.now()
        ));
    }

    private long doDownload(final DownloadTask task,
                            final InputStream inputStream,
                            final OutputStream outputStream,
                            final long totalBytes,
                            final Consumer<DownloadEvent> emitter) throws IOException {

        final byte[] buffer = new byte[bufferSize];
        long downloadedBytes = 0L;
        int bytesRead;
        final DownloadInfo info = task.info();

        while ((bytesRead = inputStream.read(buffer)) != -1) {

            if (task.isCancellationRequested()) {

                log.info("Cancelling the current download: {}", info.downloadId());
                final double progress = totalBytes > 0
                        ? Math.min(1.0d, (double) downloadedBytes / totalBytes)
                        : 0.0d;

                throw new DownloadCancelledException(
                        info,
                        downloadedBytes,
                        totalBytes,
                        progress,
                        Instant.now()
                );
            }
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
        return downloadedBytes;
    }

    private static void onCancelled(final DownloadInfo info,
                                    final Consumer<DownloadEvent> emitter,
                                    final DownloadCancelledException e) {

        log.info("HTTP download cancelled for {}", info.downloadId());

        emitter.accept(new DownloadCancelledEvent(
                info,
                e.cancelledAt(),
                e.bytesRead(),
                e.totalBytes(),
                e.progress()
        ));
    }

    private static void onError(final DownloadInfo info,
                                final Consumer<DownloadEvent> emitter,
                                final Exception e,
                                final Instant startedAt) {

        final Duration duration = Duration.between(startedAt, Instant.now());
        log.error("HTTP download failed for {}", info.downloadId(), e);
        final DownloadErrorCode errorCode = mapErrorCode(e);

        emitter.accept(new DownloadFailedEvent(
                info,
                e.getMessage() != null ? e.getMessage() : "Download failed",
                e,
                duration,
                Instant.now(),
                errorCode
        ));
    }

    private static DownloadErrorCode mapErrorCode(final Exception e) {
        if (e instanceof HttpDownloadStatusException httpStatusException) {
            return httpStatusException.mapHttpStatusToErrorCode();
        }
        return MapperExceptionUtil.mapExceptionToErrorCode(e);
    }

    private static boolean isInvalidResponseStatus(final int statusCode) {
        return statusCode < 200 || statusCode >= 300;
    }

    private HttpResponse<InputStream> doGet(final DownloadInfo info) throws IOException, InterruptedException {

        final HttpRequest request = HttpRequest.newBuilder(info.sourceUri())
                .GET()
                .build();

        return this.httpClient.send(request, HttpResponse.BodyHandlers.ofInputStream());
    }
}
