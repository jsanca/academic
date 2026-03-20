package jsanca.download.internal.strategy;

import jsanca.download.api.event.DownloadEvent;
import jsanca.download.api.model.DownloadInfo;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * Strategy responsible for executing a download.
 *
 * <p>This interface defines how a download is performed, allowing different
 * implementations (e.g., HTTP, FTP, S3) without affecting the orchestration
 * logic in the {@code DownloadManager}.
 *
 * <p>Implementations are expected to emit lifecycle events through the provided
 * {@link Consumer}, including start, progress, completion, and failure.
 * @author jsanca & elo
 */
public interface DownloadStrategy {

    /**
     * Executes a download for the given {@link DownloadInfo}.
     *
     * <p>The implementation is responsible for emitting {@link DownloadEvent}
     * instances to report progress and state changes.
     *
     * @param info    immutable identifying information for the download
     * @param emitter consumer used to emit download events
     * @throws NullPointerException if any argument is {@code null}
     */
    void download(DownloadInfo info, Consumer<DownloadEvent> emitter);

    /**
     * Validates that the required arguments are not {@code null}.
     *
     * @param info    the download information
     * @param emitter the event emitter
     * @throws NullPointerException if any argument is {@code null}
     */
    static void validate(DownloadInfo info, Consumer<DownloadEvent> emitter) {
        Objects.requireNonNull(info, "info must not be null");
        Objects.requireNonNull(emitter, "emitter must not be null");
    }
}
