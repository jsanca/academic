package jsanca.download.api.manager;

import jsanca.download.api.event.DownloadEvent;
import jsanca.download.api.model.DownloadControlResult;
import jsanca.download.api.model.DownloadInfo;
import jsanca.download.api.model.DownloadRequest;

import java.util.function.Consumer;

/**
 * Public API for managing download operations.
 *
 * <p>This interface defines the entry point for submitting downloads and
 * receiving lifecycle events.
 * @author jsanca & elo
 */
public interface DownloadManager {

    /**
     * Submits a download request.
     *
     * @param request the download request
     * @return a unique identifier for the download
     */
    DownloadInfo submit(DownloadRequest request);

    DownloadControlResult cancel(String downloadId);

    DownloadControlResult pause(String downloadId);

    DownloadControlResult resume(String downloadId);

    /**
     * Subscribes to download events.
     *
     * @param listener consumer that will receive download events
     */
    void addListener(Consumer<DownloadEvent> listener);

    /**
     * Removes a previously registered listener.
     *
     * @param listener the listener to remove
     */
    void removeListener(Consumer<DownloadEvent> listener);
}
