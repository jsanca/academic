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
     * The download is currently paused
     */
    PAUSED,

    /**
     * The download completed successfully. (terminal state)
     */
    COMPLETED,

    /**
     * The download failed due to an error.
     */
    FAILED,

    /**
     * The download was cancelled before completion.
     */
    CANCELLED;

    public boolean isTerminal() {
        return this == COMPLETED || this == FAILED || this == CANCELLED;
    }

    public boolean isActive() {
        return this == IN_PROGRESS;
    }
}
