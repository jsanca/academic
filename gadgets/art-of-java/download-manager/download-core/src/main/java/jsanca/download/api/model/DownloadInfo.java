package jsanca.download.api.model;

import java.net.URI;
import java.nio.file.Path;

/**
 * Immutable identifying information shared by all events for the same download.
 *
 * <p>This value groups the minimum stable context needed by API consumers to
 * correlate events and present meaningful information to users without exposing
 * internal downloader state.
 *
 * @param downloadId the unique identifier of the download
 * @param sourceUri the source URI being downloaded
 * @param targetPath the target file path where the download is written
 * @author jsanca & elo
 */
public record DownloadInfo(String downloadId, URI sourceUri, Path targetPath) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DownloadInfo that)) return false;
        return downloadId.equals(that.downloadId);
    }

    @Override
    public int hashCode() {
        return downloadId.hashCode();
    }
}
