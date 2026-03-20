package jsanca.download.api.event;

import jsanca.download.api.model.DownloadInfo;
import jsanca.download.api.model.DownloadStatus;

import java.time.Instant;
import java.util.Objects;

/**
 * Event emitted when a download starts.
 *
 * <p>This event marks the beginning of the download lifecycle and allows clients
 * to initialize UI state, logging, or tracking mechanisms.
 * @author jsanca & elo
 */
public record DownloadStartedEvent(
        DownloadInfo info,
        Instant occurredAt
) implements DownloadEvent {

    /**
     * Canonical constructor with validation.
     */
    public DownloadStartedEvent {
        Objects.requireNonNull(info, "info must not be null");
        Objects.requireNonNull(occurredAt, "occurredAt must not be null");
    }

    /**
     * Returns the status represented by this event.
     */
    @Override
    public DownloadStatus status() {
        return DownloadStatus.IN_PROGRESS;
    }
}
