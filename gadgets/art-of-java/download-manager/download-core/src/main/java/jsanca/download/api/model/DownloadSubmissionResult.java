package jsanca.download.api.model;

import java.time.Instant;
import java.util.Objects;

public record DownloadSubmissionResult (String downloadId, DownloadStatus status, Instant createdAt) {
    public DownloadSubmissionResult {
        Objects.requireNonNull(downloadId, "downloadId must not be null");
        Objects.requireNonNull(status, "status must not be null");
        Objects.requireNonNull(createdAt, "createdAt must not be null");
    }
}
