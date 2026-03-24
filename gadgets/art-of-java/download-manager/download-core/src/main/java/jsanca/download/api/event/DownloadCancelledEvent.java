package jsanca.download.api.event;

import jsanca.download.api.model.DownloadInfo;
import jsanca.download.api.model.DownloadStatus;

import java.time.Instant;
import java.util.Objects;

/**
 * Event emitted when a download is cancelled.
 * @author jsanca & elo
 */
public record DownloadCancelledEvent(
        DownloadInfo downloadInfo,
        Instant cancelledAt,
        long bytesRead,
        long totalBytes,
        double progress
) implements DownloadEvent {
    public DownloadCancelledEvent {
        Objects.requireNonNull(downloadInfo, "downloadInfo must not be null");
        Objects.requireNonNull(cancelledAt, "cancelledAt must not be null");

        if (bytesRead < 0) {
            throw new IllegalArgumentException("bytesRead must be >= 0");
        }
        if (totalBytes < 0) {
            throw new IllegalArgumentException("totalBytes must be >= 0");
        }
        if (bytesRead > totalBytes && totalBytes > 0) {
            throw new IllegalArgumentException("bytesRead cannot be greater than totalBytes");
        }
        if (progress < 0.0 || progress > 1.0) {
            throw new IllegalArgumentException("progress must be between 0.0 and 1.0");
        }
    }

    @Override
    public DownloadInfo info() {
        return downloadInfo;
    }

    @Override
    public DownloadStatus status() {
        return DownloadStatus.CANCELLED;
    }

    @Override
    public Instant occurredAt() {
        return cancelledAt;
    }
}
