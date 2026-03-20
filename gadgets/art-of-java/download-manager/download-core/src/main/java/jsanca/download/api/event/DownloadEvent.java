package jsanca.download.api.event;

import jsanca.download.api.model.DownloadInfo;
import jsanca.download.api.model.DownloadStatus;

import java.time.Instant;

/**
 * Base contract for all public download events.
 *
 * <p>Download events represent observable state transitions or progress updates emitted
 * by the downloader. They are part of the public API so that clients can react to the
 * lifecycle of a download in a consistent way.
 *
 * <p>All event types should carry enough shared metadata to allow correlation and
 * ordering of events belonging to the same download.
 * @author jsanca & elo
 */
public interface DownloadEvent {

    /**
     * Returns stable identifying information about the download that emitted this event.
     *
     * @return immutable download information used for correlation and display
     */
    DownloadInfo info();

    /**
     * Returns the status represented by this event.
     *
     * @return the status associated with the event
     */
    DownloadStatus status();

    /**
     * Returns the instant at which this event was created.
     *
     * @return the event creation timestamp
     */
    Instant occurredAt();

}
