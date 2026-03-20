package jsanca.download.api.model;

/**
 * Represents the lifecycle states of a download.
 *
 * <p>This enum is part of the public API and is intended to be used by clients
 * to understand and react to the current state of a download operation.
 * @author jsanca & elo
 */
public enum DownloadStatus {

    /**
     * The download has been created but not yet started.
     */
    PENDING,

    /**
     * The download is currently in progress.
     */
    IN_PROGRESS,

    /**
     * The download completed successfully.
     */
    COMPLETED,

    /**
     * The download failed due to an error.
     */
    FAILED,

    /**
     * The download was cancelled before completion.
     */
    CANCELLED
}
