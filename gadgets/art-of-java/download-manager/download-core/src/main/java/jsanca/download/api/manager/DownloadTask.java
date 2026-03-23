package jsanca.download.api.manager;

import jsanca.download.api.model.DownloadInfo;
import jsanca.download.api.model.DownloadStatus;
import jsanca.download.api.model.DownloadTaskSnapshot;
import jsanca.download.internal.execution.CancellationToken;

import java.time.Instant;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Mutable runtime representation of a download.
 *
 * <p>This class tracks the evolving internal state of a download while it is being
 * executed. It can produce immutable {@link DownloadTaskSnapshot} views for API
 * consumers without exposing its mutable internals directly.
 * @author jsanca & elo
 */
public final class DownloadTask {

    private final DownloadInfo info;
    private final CancellationToken cancellationToken;
    private final Instant createdAt;
    private final AtomicReference<DownloadStatus> status;
    private final AtomicLong downloadedBytes;
    private final AtomicLong totalBytes;
    private final AtomicReference<Instant> updatedAt;
    private final AtomicReference<String> errorMessage;

    /**
     * Creates a new download task in {@link DownloadStatus#PENDING} state.
     *
     * @param info immutable identifying information for the download
     * @throws NullPointerException if {@code info} is {@code null}
     */
    public DownloadTask(final DownloadInfo info) {
        this.info = Objects.requireNonNull(info, "info must not be null");
        this.cancellationToken = new CancellationToken();
        this.createdAt = Instant.now();
        this.status = new AtomicReference<>(DownloadStatus.PENDING);
        this.downloadedBytes = new AtomicLong(0L);
        this.totalBytes = new AtomicLong(0L);
        this.updatedAt = new AtomicReference<>(this.createdAt);
        this.errorMessage = new AtomicReference<>(null);
    }

    /**
     * Returns the immutable identifying information of the task.
     *
     * @return the download information
     */
    public DownloadInfo info() {
        return info;
    }

    /**
     * Returns the cancellation token associated with this task.
     *
     * @return the cooperative cancellation token
     */
    public CancellationToken cancellationToken() {
        return cancellationToken;
    }

    /**
     * Returns whether cancellation has been requested for this task.
     *
     * @return {@code true} if cancellation was requested; {@code false} otherwise
     */
    public boolean isCancellationRequested() {
        return cancellationToken.isCancelled();
    }

    /**
     * Requests cancellation for this task.
     */
    public void requestCancellation() {
        cancellationToken.cancel();
        touch();
    }

    /**
     * Returns the current task status.
     *
     * @return the current download status
     */
    public DownloadStatus status() {
        return status.get();
    }

    /**
     * Marks the task as started.
     */
    public void markStarted() {
        updateStatus(DownloadStatus.IN_PROGRESS);
    }

    /**
     * Updates the current progress values and refreshes the last-updated timestamp.
     *
     * @param downloadedBytes the downloaded bytes so far
     * @param totalBytes the expected total bytes
     * @throws IllegalArgumentException if any value is negative
     */
    public void updateProgress(final long downloadedBytes, final long totalBytes) {
        if (downloadedBytes < 0) {
            throw new IllegalArgumentException("downloadedBytes must be >= 0");
        }
        if (totalBytes < 0) {
            throw new IllegalArgumentException("totalBytes must be >= 0");
        }

        this.downloadedBytes.set(downloadedBytes);
        this.totalBytes.set(totalBytes);
        touch();
    }

    /**
     * Marks the task as completed and updates final progress values.
     *
     * @param downloadedBytes the final downloaded bytes
     * @param totalBytes the final total bytes
     */
    public void markCompleted(final long downloadedBytes, final long totalBytes) {
        updateProgress(downloadedBytes, totalBytes);
        updateStatus(DownloadStatus.COMPLETED);
    }

    /**
     * Marks the task as failed with the provided message.
     *
     * @param message the failure message, may be {@code null}
     */
    public void markFailed(final String message) {
        this.errorMessage.set(message);
        updateStatus(DownloadStatus.FAILED);
    }

    /**
     * Marks the task as cancelled.
     */
    public void markCancelled() {
        updateStatus(DownloadStatus.CANCELLED);
    }

    /**
     * Creates an immutable snapshot of the current task state.
     *
     * @return a snapshot of the current task state
     */
    public DownloadTaskSnapshot snapshot() {
        return new DownloadTaskSnapshot(
                info,
                status.get(),
                downloadedBytes.get(),
                totalBytes.get(),
                createdAt,
                updatedAt.get(),
                errorMessage.get()
        );
    }

    private void updateStatus(final DownloadStatus newStatus) {
        status.set(Objects.requireNonNull(newStatus, "newStatus must not be null"));
        touch();
    }

    private void touch() {
        updatedAt.set(Instant.now());
    }
}
