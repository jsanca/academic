
package jsanca.download.api.event;

import jsanca.download.api.model.DownloadInfo;
import jsanca.download.api.model.DownloadStatus;

import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;
import java.util.Objects;

/**
 * Event emitted when a download completes successfully.
 *
 * <p>This event exposes the final file location together with useful completion metadata
 * such as the total downloaded size, an optional checksum, and the total duration of the
 * download operation.
 * @author jsnca & elo
 */
public record DownloadCompletedEvent(
        DownloadInfo info,
        Path finalPath,
        long totalBytes,
        String checksum,
        Duration duration,
        Instant occurredAt
) implements DownloadEvent {

    /**
     * Canonical constructor with validation.
     *
     * @throws NullPointerException if any required reference is {@code null}
     * @throws IllegalArgumentException if {@code totalBytes} is negative
     */
    public DownloadCompletedEvent {
        Objects.requireNonNull(info, "info must not be null");
        Objects.requireNonNull(finalPath, "finalPath must not be null");
        Objects.requireNonNull(duration, "duration must not be null");
        Objects.requireNonNull(occurredAt, "occurredAt must not be null");

        if (totalBytes < 0) {
            throw new IllegalArgumentException("totalBytes must be >= 0");
        }

        checksum = normalizeChecksum(checksum);
    }

    /**
     * Returns the status represented by this event.
     *
     * @return {@link jsanca.download.api.model.DownloadStatus#COMPLETED}
     */
    @Override
    public DownloadStatus status() {
        return DownloadStatus.COMPLETED;
    }

    /**
     * Returns whether a checksum is present.
     *
     * @return {@code true} when a non-blank checksum is available
     */
    public boolean hasChecksum() {
        return checksum != null;
    }

    private static String normalizeChecksum(String checksum) {
        if (checksum == null) {
            return null;
        }

        String normalized = checksum.trim();
        return normalized.isEmpty() ? null : normalized;
    }
}
