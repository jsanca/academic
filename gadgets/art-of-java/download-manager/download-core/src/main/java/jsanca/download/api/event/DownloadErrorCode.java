package jsanca.download.api.event;

/**
 * Represents categorized error codes for download failures.
 *
 * <p>These codes allow clients (UI, services) to distinguish between different
 * failure scenarios and decide how to react (e.g., retry, ignore, show specific UI state).
 */
public enum DownloadErrorCode {

    // --- Network / transport ---
    NETWORK_ERROR,
    TIMEOUT,
    CONNECTION_REFUSED,
    INTERRUPTED,

    // --- HTTP specific ---
    HTTP_NOT_FOUND,           // 404
    HTTP_UNAUTHORIZED,        // 401
    HTTP_FORBIDDEN,           // 403
    HTTP_BAD_REQUEST,         // 400
    HTTP_SERVER_ERROR,        // 5xx
    HTTP_SERVICE_UNAVAILABLE, // 503
    HTTP_INVALID_RESPONSE,    // non 2xx not mapped above

    // --- I/O ---
    IO_ERROR,

    // --- Generic / fallback ---
    INVALID_RESPONSE,
    UNKNOWN
}
