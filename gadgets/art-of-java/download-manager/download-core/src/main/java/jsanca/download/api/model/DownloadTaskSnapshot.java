package jsanca.download.api.model;

import java.time.Instant;

/**
 * Immutable snapshot representing the current state of a download task.
 *
 * <p>This is a read-only view exposed to API consumers, decoupled from the
 * internal mutable DownloadTask.
 * @author jsanca & elo
 */
public record DownloadTaskSnapshot(
        DownloadInfo info,
        DownloadStatus status,
        long downloadedBytes,
        long totalBytes,
        Instant createdAt,
        Instant updatedAt,
        String errorMessage
) {
}
