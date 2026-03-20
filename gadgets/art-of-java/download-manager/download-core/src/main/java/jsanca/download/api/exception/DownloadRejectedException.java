package jsanca.download.api.exception;

public class DownloadRejectedException extends RuntimeException {

    public DownloadRejectedException() {
    }

    public DownloadRejectedException(String message) {
        super(message);
    }

    public DownloadRejectedException(String message, Throwable cause) {
        super(message, cause);
    }

    public DownloadRejectedException(Throwable cause) {
        super(cause);
    }

    public DownloadRejectedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
