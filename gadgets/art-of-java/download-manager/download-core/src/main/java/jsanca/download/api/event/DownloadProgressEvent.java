package jsanca.download.api.event;

import jsanca.download.api.model.DownloadInfo;
import jsanca.download.api.model.DownloadStatus;

import java.time.Instant;
import java.util.Objects;

/**
 * Event emitted while a download is in progress.
 *
 * <p>This event provides incremental progress information including the number of bytes
 * downloaded so far and the total expected size (when known).
 * @author jsanca & elo
 */
public record DownloadProgressEvent(
        DownloadInfo info,
        long downloadedBytes,
        long totalBytes,
        Instant occurredAt
) implements DownloadEvent {

    /**
     * Canonical constructor with validation.
     */
    public DownloadProgressEvent {
        Objects.requireNonNull(info, "info must not be null");
        Objects.requireNonNull(occurredAt, "occurredAt must not be null");

        if (downloadedBytes < 0) {
            throw new IllegalArgumentException("downloadedBytes must be >= 0");
        }

        if (totalBytes < 0) {
            throw new IllegalArgumentException("totalBytes must be >= 0");
        }
    }

    /**
     * Returns the status represented by this event.
     */
    @Override
    public DownloadStatus status() {
        return DownloadStatus.IN_PROGRESS;
    }

    /**
     * Convenience method to check if total size is known.
     */
    public boolean hasTotalBytes() {
        return totalBytes > 0;
    }

    /**
     * Calculates progress percentage when total size is known.
     */
    public double percentage() {
        if (!hasTotalBytes()) {
            return 0.0d;
        }
        double pct = ((double) downloadedBytes / totalBytes) * 100.0d;
        return Math.min(100.0d, Math.max(0.0d, pct));
    }
}
