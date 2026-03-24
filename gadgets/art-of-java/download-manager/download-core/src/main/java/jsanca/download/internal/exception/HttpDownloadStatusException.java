package jsanca.download.internal.exception;

import jsanca.download.api.event.DownloadErrorCode;
import jsanca.download.api.model.DownloadInfo;

import java.io.IOException;
import java.util.Objects;

public  class HttpDownloadStatusException extends IOException {

    private final DownloadInfo info;
    private final int statusCode;

    public HttpDownloadStatusException(final DownloadInfo info,
                                        final int statusCode) {
        super("Unexpected HTTP status code " + statusCode + " for download " + info.downloadId());
        this.info = Objects.requireNonNull(info, "info must not be null");
        this.statusCode = statusCode;
    }

    public DownloadInfo info() {
        return info;
    }

    public int statusCode() {
        return statusCode;
    }

    public DownloadErrorCode mapHttpStatusToErrorCode() {
        return switch (this.statusCode) {
            case 400 -> DownloadErrorCode.HTTP_BAD_REQUEST;
            case 401 -> DownloadErrorCode.HTTP_UNAUTHORIZED;
            case 403 -> DownloadErrorCode.HTTP_FORBIDDEN;
            case 404 -> DownloadErrorCode.HTTP_NOT_FOUND;
            case 503 -> DownloadErrorCode.HTTP_SERVICE_UNAVAILABLE;
            default -> {
                if (statusCode >= 500 && statusCode <= 599) {
                    yield DownloadErrorCode.HTTP_SERVER_ERROR;
                }
                yield DownloadErrorCode.HTTP_INVALID_RESPONSE;
            }
        };
    }
}
