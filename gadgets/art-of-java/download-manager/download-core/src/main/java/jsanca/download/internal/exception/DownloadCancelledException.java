package jsanca.download.internal.exception;

import jsanca.download.api.model.DownloadInfo;

import java.time.Instant;

public class DownloadCancelledException extends RuntimeException {

    private final DownloadInfo info;
    private final long downloadedBytes;
    private final long totalBytes;
    private final double progress;

    private final Instant cancelledAt;

    public DownloadCancelledException(final DownloadInfo info,
                                      final long downloadedBytes,
                                      final long totalBytes,
                                      final double progress,
                                      final Instant cancelledAt) {

        this.info = info;
        this.downloadedBytes = downloadedBytes;
        this.totalBytes = totalBytes;
        this.progress = progress;
        this.cancelledAt = cancelledAt;
    }

    public DownloadInfo info() {
        return info;
    }

    public Instant cancelledAt() {
        return cancelledAt;
    }

    public long bytesRead() {
        return downloadedBytes;
    }

    public long totalBytes() {
        return totalBytes;
    }

    public double progress() {
        return this.progress;
    }
}
