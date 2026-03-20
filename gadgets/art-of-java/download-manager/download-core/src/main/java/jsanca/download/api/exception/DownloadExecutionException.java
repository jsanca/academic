package jsanca.download.api.exception;

public class DownloadExecutionException extends RuntimeException {

    public DownloadExecutionException() {
    }

    public DownloadExecutionException(String message) {
        super(message);
    }

    public DownloadExecutionException(String message, Throwable cause) {
        super(message, cause);
    }

    public DownloadExecutionException(Throwable cause) {
        super(cause);
    }

    public DownloadExecutionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
