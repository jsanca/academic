package jsanca.download.api.event;

import jsanca.download.api.model.DownloadInfo;
import jsanca.download.api.model.DownloadStatus;

import java.time.Duration;
import java.time.Instant;
import java.util.Objects;

/**
 * Event emitted when a download fails.
 *
 * <p>This event exposes failure details together with timing information so that
 * clients can log, retry, or present meaningful feedback to users.
 * @author jsanca & elo
 */
public record DownloadFailedEvent(
        DownloadInfo info,
        String errorMessage,
        Throwable cause,
        Duration duration,
        Instant occurredAt,
        DownloadErrorCode errorCode
) implements DownloadEvent {

    /**
     * Canonical constructor with validation.
     */
    public DownloadFailedEvent {
        Objects.requireNonNull(info, "info must not be null");
        Objects.requireNonNull(duration, "duration must not be null");
        Objects.requireNonNull(occurredAt, "occurredAt must not be null");

        errorMessage = normalizeMessage(errorMessage, cause);
    }

    /**
     * Returns the status represented by this event.
     */
    @Override
    public DownloadStatus status() {
        return DownloadStatus.FAILED;
    }

    /**
     * Returns whether a cause is present.
     */
    public boolean hasCause() {
        return cause != null;
    }

    private static String normalizeMessage(String message, Throwable cause) {
        if (message != null && !message.isBlank()) {
            return message.trim();
        }

        if (cause != null && cause.getMessage() != null) {
            return cause.getMessage();
        }

        return "Download failed";
    }
}
