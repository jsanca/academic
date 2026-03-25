package jsanca.download.api.event;

import jsanca.download.api.model.DownloadInfo;
import jsanca.download.api.model.DownloadStatus;

import java.time.Instant;

/**
 * Event emitted when a paused download is resumed and continues execution.
 * @author jsanca & elo
 */
public record DownloadResumedEvent(
        DownloadInfo info,
        long downloadedBytes,
        long totalBytes,
        Instant occurredAt
) implements DownloadEvent {

    @Override
    public DownloadStatus status() {
        return DownloadStatus.IN_PROGRESS;
    }
}
