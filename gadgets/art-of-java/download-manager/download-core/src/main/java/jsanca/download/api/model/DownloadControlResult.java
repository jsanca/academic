package jsanca.download.api.model;

/**
 * Result of a control operation applied to a download (e.g., cancel, pause, resume).
 *
 * <p>This object communicates both the intent of the operation and its outcome,
 * allowing API consumers to understand whether the action was accepted, rejected,
 * or could not be performed due to the current state of the download.
 *
 * <p>It may also include a {@link DownloadTaskSnapshot} representing the current
 * state of the download at the moment the operation was processed.
 *
 * @param downloadId the identifier of the download the operation was applied to
 * @param action the control action that was requested (e.g., cancel, pause, resume)
 * @param status the outcome of the control operation
 * @param message optional human-readable message providing additional context
 * @param snapshot optional snapshot of the download state after the operation
 * @author jsanca & elo
 */
public record DownloadControlResult(
        String downloadId,
        DownloadControlAction action,
        DownloadControlStatus status,
        String message,
        DownloadTaskSnapshot snapshot
) {
}
